const hobbieNum = 5;
let hobbies = [];
let currentDisplayUserModalID = -1;
let userInfoModalsContainer = ['changePassword', 'changeEmail','changeUsername', 'changeLocation'];
let pictures = [];




function requestSent(){
    let xhr = new XMLHttpRequest();
    console.log('blalala');

    xhr.open('GET','AsyncServlet', true);

    xhr.onload = function (){
        const ragacSurati = JSON.parse(this.responseText).toString();
        console.log(ragacSurati);
        console.log(typeof (ragacSurati));
        document.getElementById('ragacasurati').setAttribute('src',ragacSurati);
    }
    xhr.send();
}

$(document).ready(function() {

    let suggestedUserID = null;
    let suggestedUserName = null;
    var suggestedUserAge = null;
    var sex = $("#suggestedUserGender").text();
    var id = $("#currentUserID").text();
    var profileID = $("#currentUserProfileID").text();
    var image = null;
    let blankSuggestionImg = "Content/Images/" + (((sex === 'MALE') ? 'DEFAULT_THUMB_FEMALE.jpg' : 'DEFAULT_THUMB_MALE.jpg'));
    var chats = null;
    var hobbies = null;

    console.log("here? 1")
    function getSuggestedUserInfo() {
        $.ajax({
            async: false,
            type: "POST",
            url: "GetSuggestedUserServlet",
            data: {"userID": id},
            success: function (msg) {
                try {
                    let response = JSON.parse(msg).status;
                    if (response == 0) {
                        suggestedUserID = null;
                    }
                } catch (e) {
                    suggestedUserID = msg[0];
                    suggestedUserName = msg[1];
                    suggestedUserAge = msg[2];
                }
            },
            error: function (msg) {
                alert("no more suggestions");
            }
        });
    }

    function getSuggestedUserImages() {
        if (suggestedUserID != null) {
            $.ajax({
                async: false,
                type: "POST",
                url: "GetUserImagesServlet",
                dataType: "json",
                data: {"suggestedPersonalId": suggestedUserID},
                success: function (msg) {
                    image = msg;
                },
                error: function (msg) {
                    alert("No images");
                }
            });
        }
    }

    function displaySuggestedUser() {
        if (suggestedUserID == null) {
            $("#suggestionImage").attr("src", blankSuggestionImg);
            $("#suggestionName").text("There are no more suggestions");
            $("#LikeButton").attr("style", "background: grey");
            $("#LikeButton").attr('disabled', 'true');
            $("#DislikeButton").attr("style", "background: grey");
            $("#DisLikeButton").attr('disabled', 'true');
        } else {
            $("#suggestionImage").attr("src", image);
            $("#suggestionName").text(suggestedUserName);
        }

    }

    function displayChats() {
        console.log(chats)
        if (chats.length != 0) {
            for(let i=0; i<chats.length; i++)
            {
                let chatImage = chats[i].image.length == 0 ? blankSuggestionImg : chats[i].image;
                console.log(chatImage);
                $("#chatsContainerBody").append( "<div class=\"currentChatContainer\">\n" +
                    "            <img class=\"chatUserIcon\" src=" + chatImage + ">\n" +
                    "            <span>" + chats[i].chat_buddy_name + "</span>\n" +
                    "        </div>");
            }
        }
    }

    function getChats() {
            $.ajax({
                async: false,
                type: "POST",
                url: "GetAllMatchesServlet",
                dataType: "json",
                data: {"user_Profile_ID": profileID, "sex": sex},
                success: function (msg) {
                    console.log(msg);
                    chats = msg;
                },
                error: function (msg) {
                    alert("No chats");
                }
            });
    }

    function act(action) {
            $.ajax({
                async: false,
                type: "POST",
                url: "LikeAndDislikeActionServlet",
                dataType: "json",
                data: {"actor": profileID, "subject": suggestedUserID, "action": action, "sex": sex},
                success: function (msg) {
                    const currMsg = JSON.stringify(msg);
                    let response = JSON.parse(currMsg);
                    let status = response.status;
                    if (status == 1) {
                        getChats();
                        displayChats();
                        getSuggestedUserInfo();
                        getSuggestedUserImages();
                        displaySuggestedUser();
                    } else if (status == 2) {
                        suggestedUserID = null;
                        suggestedUserName = null;
                        suggestedUserAge = null;
                        getChats();
                        displayChats();
                        displaySuggestedUser();
                    } else if (status == 3) {
                        getChats();
                        console.log(chats[0]);
                        displayChats();
                        getSuggestedUserInfo();
                        getSuggestedUserImages();
                        displaySuggestedUser();
                    } else {
                        alert("Unexpected error");
                    }
                },
                error: function (msg) {
                    alert("No images");
                }
            });
    }

    getSuggestedUserInfo();
    getSuggestedUserImages();
    displaySuggestedUser();
    getChats();
    displayChats();

    $("#LikeButton").on("click", function () {
            act(1);
    });

    $("#DislikeButton").on("click", function () {
            act(-1);
    });


    function bs_input_file() {
        $(".input-file").before(
            function() {
                if ( ! $(this).prev().hasClass('input-ghost') ) {
                    const Reader = new FileReader();
                    let imagePreview = document.getElementById('ImagePreview');
                    var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
                    element.attr("name",$(this).attr("name"));
                    element.change(function(){
                        Reader.readAsDataURL(this.files[0]);

                        Reader.addEventListener('load', () => {
                            imagePreview.setAttribute('src', Reader.result);

                        })
                        imagePreview.setAttribute('src', Reader.result);
                        element.next(element).find('input').val((element.val()).split('\\').pop());
                    });
                    $(this).find("button.btn-choose").click(function(){
                        element.click();
                    });
                    $(this).find("button.btn-reset").click(function(){
                        element.val(null);
                        $(this).parents(".input-file").find('input').val('');
                    });
                    $(this).find('input').css("cursor","pointer");
                    $(this).find('input').mousedown(function() {
                        $(this).parents('.input-file').prev().click();
                        return false;
                    });
                    return element;
                }
            }
        );
    }

    bs_input_file();

    $("#uploadBtn").on("click", function() {
        var url = "ImageDownloadServlet";
        var form = $("#sampleUploadFrm")[0];
        var data = new FormData(form);
        $("")
        $.ajax({
            type : "POST",
            encType : "multipart/form-data",
            url : url,
            cache : false,
            processData : false,
            contentType : false,
            data : data,
            success : function(msg) {
                var response = JSON.parse(msg);
                var status = response.status;
                if (status == 1) {
                    $("#errorMessage").text("You can upload at most 3 images");
                } else if (status == 2){
                    $("#errorMessage").text("The image must be .jpg .png .jpeg or .gif file")
                }else if(status == 3){
                    $("#errorMessage").text("The image was uploaded successfully")
                }else{
                    $("#errorMessage").text("Couldn't upload file")
                }
            },
            error : function(msg) {
                $("#errorMessage").text("Couldn't upload file");
            }
        });
    });

    $("#changeEmailButton").on("click",function(){
        var newEmail = $("#email").val();
        var url = "ChangeEmailServlet";
        $.ajax({
            type : "POST",
            url : url,
            data : {"email":newEmail},
            success : function(msg) {
                var response = JSON.parse(msg);
                var status = response.status;
                if (status == 1) {
                    $("#changeEmailError").text("Invalid Email");
                } else if (status == 2){
                    $("#changeEmailError").text("Email has been updates successfully")
                }else if(status == 3){
                    $("#changeEmailError").text("Email could not be uploaded")
                }else{
                    $("#changeEmailError").text("Couldn't upload email")
                }
            },
            error : function(msg) {
                $("#changeEmailError").text("Email could not be uploaded");
            }
        });
    });

    $("#changeUsernameID").on("click",function(){
        var newUsername = $("#username").val();
        var url = "ChangeUsernameServlet";
        $.ajax({
            type : "POST",
            url : url,
            data : {"username":newUsername},
            success : function(msg) {
                var response = JSON.parse(msg);
                var status = response.status;
                if (status == 1) {
                    $("#changeUsernameError").text("Username must consist of at least 4 characters");
                } else if (status == 2){
                    $("#changeUsernameError").text("Username has been updates successfully")
                }else if(status == 3){
                    $("#changeUsernameError").text("Username could not be updated")
                }else{
                    $("#changeUsernameError").text("Couldn't update Username")
                }
            },
            error : function(msg) {
                $("#changeUsernameError").text("Username could not be updated");
            }
        });
    });

    $("#changePasswordButton").on("click",function(){
        var oldPass = $("#oldPassword").val();
        var newPass = $("#newPassword").val();
        var newPassRepeat = $("#newPasswordRepeat").val();
        var url = "ChangePasswordServlet";
        $.ajax({
            type : "POST",
            url : url,
            data : {"oldPassword":oldPass,"newPassword":newPass,"newPasswordRepeat":newPassRepeat},
            success : function(msg) {
                var response = JSON.parse(msg);
                var status = response.status;
                if (status == 1) {
                    $("#changePasswordError").text("Please fill new password fields correctly");
                } else if (status == 2){
                    $("#changePasswordError").text("New password must be different from the current")
                }else if(status == 3){
                    $("#changePasswordError").text("Please enter your current password correctly")
                }else if(status == 4){
                    $("#changePasswordError").text("Your password has been updated successfully!")
                }else{
                    $("#changePasswordError").text("Your password could not be updated");
                }
            },
            error : function(msg) {
                $("#changePasswordError").text("Your password could not be updated");
            }
        });
    });


    $("#changeLocationButton").on("click",function(){
        var newCity = $("#OutputCity").val();
        var url = "ChangeLocationServlet";
        $.ajax({
            type : "POST",
            url : url,
            data : {"city":newCity},
            success : function(msg) {
                var response = JSON.parse(msg);
                var status = response.status;
                if (status == 1) {
                    $("#changeLocationError").text("New location must differ from the current one");
                } else if (status == 2){
                    $("#changeLocationError").text("Location was updated successfully!")
                }else if(status == 3){
                    $("#changeLocationError").text("Please choose your location")
                }else{
                    $("#changeLocationError").text("Your location could not be updated");
                }
            },
            error : function(msg) {
                $("#changeLocationError").text("Your location could not be updated");
            }
        });
    });

});


/*function addPicturesTimeOut(){
    let uploadFileInput = document.getElementById('fileUploadInputId');
    let imagePreview = document.getElementById('ImagePreview');
    const Reader = new FileReader();
    uploadFileInput.addEventListener('change', function (){
        console.log('shemovida');
        Reader.readAsDataURL(this.files[0])


        Reader.addEventListener('load', () => {
            console.log(Reader.result);
            imagePreview.setAttribute('src', Reader.result);
            uploadFileInput.setAttribute('value', pictures);
        })
        console.log(pictures.length);
    });
}*/


function init(){
    setTimeout(checkMessages, 200);
   // setTimeout(addPicturesTimeOut, 200);
}
init();


function checkMessages(){
    if(document.getElementById('isFullyRegistered').textContent == "Options"){
        console.log("Fully Registered");
        document.querySelector('.signOutButton').style.display = 'none';
        document.querySelector('.completeRegisterButton').style.display = 'none';
    }else{
        console.log("");
        document.getElementById('AccountInfo').style.display = 'none';
    }

}

function toggleModal(modalId){
    let currModal = document.getElementById(modalId);
    if(currModal.style.display === 'none'|| currModal.style.display === "" ) currModal.style.display = "flex"
    else currModal.style.display = "none";
}

function hobbiesAppear(){
    document.querySelector('.hobbieContainer').style.display = 'block';
    document.querySelector('.registerBody').style.display = 'none';
}

function disMissHobbies(){
    document.querySelector('.hobbieContainer').style.display = 'none';
    document.querySelector('.registerBody').style.display = 'flex';
}

window.onclick = function (event){
    if(event.target == document.getElementById('CompleteRegistrationModal'))
        document.getElementById('CompleteRegistrationModal').style.display = 'none';
    if(event.target == document.getElementById('uploadImageContainer'))
        document.getElementById('uploadImageContainer').style.display = 'none';
    for(let i = 0; i < userInfoModalsContainer.length; i++){
        if(event.target == document.getElementById(userInfoModalsContainer[i]))
            document.getElementById(userInfoModalsContainer[i]).style.display = 'none';
    }
}

function chooseHobbie(element){
    if(document.getElementById(element).className === 'hobbieElementActive'){
        document.getElementById(element).className = 'hobbieElement';
        removeHobbie(element);
    } else if(hobbies.length < hobbieNum){
        document.getElementById(element).className = 'hobbieElementActive';
        hobbies.push(element);
    }
    setHobbies();
    document.querySelector('.hobbieButton').setAttribute("value", hobbies);
    console.log(document.querySelector('.hobbieButton').getAttribute("value"));
}

function toggleSex(curr){
    if(curr === 'Male'){
        document.getElementById('MaleSex').style.background = "#FFF";
        document.getElementById('FemaleSex').style.background = 'none';
        document.querySelector('.genderInput').setAttribute("value","MALE");
        console.log(document.querySelector('.genderInput').getAttribute('value'));

    }else{
        document.getElementById('FemaleSex').style.background = "#FFF";
        document.getElementById('MaleSex').style.background = 'none';
        document.querySelector('.genderInput').setAttribute("value","FEMALE");
        console.log(document.querySelector('.genderInput').getAttribute('value'));
    }
}

function removeHobbie(element){
    hobbies = hobbies.filter(v => v !== element);
}


function changeDate(id, number){
    document.getElementById(id).textContent = number;
    console.log(number);
    if(id === 'city'){
        setCity();
    }

    /*if(id === 'userDay') document.getElementById('dayDropDown').style.display = "none";
    else document.getElementById('MonthDropDown').style.display = "none";*/
}


function dataDismiss(element){
    document.getElementById(element).style.display = 'none';
}

function disPlayDropDown(element){
    document.getElementById(element).style.display = 'block';
}

function setDate(){
    let date = "";
    date+= document.getElementById('userDay').textContent+ "/";
    date+= document.getElementById('userMonth').textContent + "/";
    date+= document.querySelector('.elementYear').value.toString();
    document.getElementById('dateData').value = date;

}
function setCity(){
    let city;
    city = document.getElementById('city').textContent;
    console.log(city);
    document.getElementById('OutputCity').value = city;
}
function setHobbies(){
    hobbies.sort();
    document.getElementById('OutputHobbies').value = hobbies.join(',');
    console.log(document.getElementById('OutputHobbies').value);
}

function displayAccountInfo(){
    let content = document.querySelector('.accountInfoContent');
    let bars = document.getElementById('AccountInfo');
    content.style.display = 'flex';

    bars.style.color = '#E67826';
    bars.style.background = 'white';
    bars.style.borderRadius = "4px 4px 0 0";
    bars.style.marginTop = "-10px";
    bars.style.padding = "10px 8px";

}

function dismissAccountModal(){
    let content = document.querySelector('.accountInfoContent');
    let bars = document.getElementById('AccountInfo');
    content.style.display = "none";
    bars.style.color = "white";
    bars.style.background = "none";
    bars.style.marginTop = "0";
    bars.style.padding = "0";
    // document.getElementById(userInfoModalsContainer[currentDisplayUserModalID]).style.display = 'none';
}

function displayInfoModal(id){
    if(currentDisplayUserModalID != -1)
        document.getElementById(userInfoModalsContainer[currentDisplayUserModalID]).style.display = 'none';
    document.getElementById(userInfoModalsContainer[id]).style.display = 'flex';
    currentDisplayUserModalID = id;
}

function addPicturesToProfile(){
    if(pictures.length < 3){
        pictures.push(uploadFileInput.value);
    }

}



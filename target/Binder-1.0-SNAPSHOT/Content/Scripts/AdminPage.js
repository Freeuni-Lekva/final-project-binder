let chooseBannedUser;

$(document).ready(function() {

    let id = null;
    let userInfo = null;
    let bannedUsers = null;

    //user info
    let currentUserUsername = $('#currentUserUsername');
    let currentUserStatus = $('#currentUserStatus');
    let currentUserName = $('#currentUserName');
    let currentUserSex = $('#currentUserSex');
    let currentUserSurname = $('#currentUserSurname');
    let currentUserEmail = $('#currentUserEmail');
    let currentUserCity = $('#currentUserCity');
    let currentUserAge = $('#currentUserAge');
    let currentUserImage = $('.userContainerImage');

    let searchForUser = $('.searchForUserInput');

    //status Buttons
    let BanButton = $('.userBanButton');
    let UnbanButton = $('.userUnbanButton');
    let isAdmin = $('.userIsAdminDisplay');

    getBannedUsers();
    console.log(bannedUsers);
    displayBannedUsers();

    BanButton.on('click', function(){
        BanButton.css('display', 'none');
        UnbanButton.css('display', 'flex');
        banUser(id);
    });
    UnbanButton.on('click',function (){
        UnbanButton.css('display', 'none');
        BanButton.css('display', 'flex');
        unbanUser(id);
    });

    function setCurrentUserInfo(){
        currentUserImage.attr('src', "Content/Images/DEFAULT_UNI_PROFILE.jpg");
        console.log(userInfo.image);
        currentUserUsername.text(`Username: ${userInfo.username}`);
        let currentStatus;
        if(userInfo.isBanned){
            currentStatus = 'Banned';
        }else{
            currentStatus = 'Active';
        }
        currentUserStatus.text(`Status: ${currentStatus}`);
        currentUserName.text(`Name: ${userInfo.name}`);
        currentUserSurname.text(`Surname: ${userInfo.surname}`);
        currentUserSex.text(`Sex: ${userInfo.sex}`);
        currentUserEmail.text(`Email: ${userInfo.email}`);
        if(userInfo.has_user_profile){
            currentUserAge.text(`Age: ${userInfo.age}`);
            currentUserCity.text(`City: ${userInfo.city}`);
            if(userInfo.image != "null"){
                currentUserImage.attr('src', userInfo.image);
            }
        }else{
            currentUserAge.text(`Age: N/A`);
            currentUserCity.text('City: N/A');
        }
    }


    searchForUser.on('keydown', function (e){
        if(e.key == "Enter" && searchForUser.val() !== ""){
            id = searchForUser.val();
            getUserInfo();
            searchForUser.val('');
        }
    });

    $('#searchForUserId').on('click', function(){
        if(searchForUser.val() !== ''){
            id =  searchForUser.val();
            getUserInfo();
            searchForUser.val('');
        }
    })

    function displayCorrectStatusButton(){
        BanButton.css('display', 'none');
        UnbanButton.css('display', 'none');
        isAdmin.css('display', 'none');
        if(userInfo.isAdmin){
            isAdmin.css('display', 'flex')
        }else{
            console.log('shemovida elshi', userInfo.isBanned);
            if(userInfo.isBanned){
                UnbanButton.css('display', 'flex');
            }else BanButton.css('display', 'flex');
        }
    }
     chooseBannedUser = function (currid){
        id = currid;
        getUserInfo();
    }

    function displayBannedUsers(){
        $('.bannedUserBody').html("");
        if(bannedUsers.length != 0){
            for(let i = 0; i < bannedUsers.length; i++){
                $('.bannedUserBody').append(`<div class='bannedUserValue' 
                onclick="chooseBannedUser(${bannedUsers[i].user_id})">
                    <span>ID: ${bannedUsers[i].user_id}</span>
                    <span>Username: ${bannedUsers[i].username}</span>
                </div>`)
            }
        }
    }

    function getUserInfo() {
        $.ajax({
            async: false,
            type: "POST",
            url: "GetUserDetailsServlet",
            data: {"userID": id},
            success: function (msg) {
                userInfo = JSON.parse(msg);

                if(userInfo.status == 1) alert("user was not found");
                else if(userInfo.status == 2) alert("You do not have permission!");
                else {
                    setCurrentUserInfo();
                    $('.userBoxContainer').css('display', 'flex');
                    displayCorrectStatusButton();
                }
            },
            error: function (msg) {
                alert("Unexpected Error!");
            }
        });
    }

    function getBannedUsers(){
        console.log('shemovida aq');
        $.ajax({
            async: false,
            type: "POST",
            url: "GetBannedUsersServlet",
            success: function (msg) {
                bannedUsers = JSON.parse(msg);
                if(bannedUsers.status == 2){
                    alert("Something is Wrong");
                }else if (bannedUsers.status == 3){
                    alert("You do not have permission!");
                }
            },
            error: function (msg) {
                alert("Unexpected Error!");
            }
        });
    }

    function banUser(user_id) {
        $.ajax({
            async: false,
            type: "POST",
            url: "BanUserServlet",
            data: {"userID": user_id},
            success: function (msg) {
                let response = JSON.parse(msg);
                let status = response.status;
                if (status == 1) {
                    console.log("User got Banned Successfully!");
                    getBannedUsers();
                    displayBannedUsers();
                }
                else if (status == 2){
                    alert("Something went wrong");
                }else if (status == 3){
                    alert("You do not have permission!");
                }
            },
            error: function (msg) {

                alert("Unexpected Error!");
            }
        });
    }

    function unbanUser(user_id){
        $.ajax({
            async: false,
            type: "POST",
            url: "UnbanUserServlet",
            data: {"userID": user_id},
            success: function (msg) {
                let response = JSON.parse(msg);
                let status = response.status;
                if (status == 1) {
                    getBannedUsers();
                    displayBannedUsers();
                    console.log("User got unbanned Successfully!");
                }
                else if (status == 2){
                    console.log("Something went wrong");
                }
            },
            error: function (msg) {
                alert("Unexpected Error!");
            }
        });
    }
});
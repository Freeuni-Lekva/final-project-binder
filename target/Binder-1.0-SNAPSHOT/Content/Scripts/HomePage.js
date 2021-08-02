const hobbieNum = 5;
let hobbies = [];
let currentDisplayUserModalID = -1;
let userInfoModalsContainer = ['changePassword', 'changeEmail','changeUsername', 'changeLocation'];
let pictures = [];

function addPicturesTimeOut(){
    let uploadFileInput = document.getElementById('fileUploadInputId');
    let imagePreview = document.getElementById('ImagePreview');
    const Reader = new FileReader();
    uploadFileInput.addEventListener('change', function (){
        Reader.readAsDataURL(this.files[0])


        Reader.addEventListener('load', () => {
            console.log(Reader.result);
            imagePreview.setAttribute('src', Reader.result);
            uploadFileInput.setAttribute('value', pictures);
        })
        console.log(pictures.length);
    });
}


function init(){
    setTimeout(checkMessages, 200);
    setTimeout(addPicturesTimeOut, 200);
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



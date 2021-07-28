function toggleModal(modalId){
    let currModal = document.getElementById(modalId);
    if(currModal.style.display === 'none'|| currModal.style.display === "" ) currModal.style.display = "flex"
    else currModal.style.display = "none";
}
const hobbieNum = 5;
let hobbies = [];



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
    console.log('shemovida');
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


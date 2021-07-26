function toggleModal(modalId){
    let currModal = document.getElementById(modalId);
    if(currModal.style.display == 'none'|| currModal.style.display == "" ) currModal.style.display = "flex"
    else currModal.style.display = "none";
}
const hobbieNum = 5;
let currentNumberOfHobbies = 0;

let hobbies = new Array(hobbieNum);



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
    if(document.getElementById(element).className == 'hobbieElementActive'){

        document.getElementById(element).className = 'hobbieElement';
        removeHobbie(element);
    } else if(currentNumberOfHobbies < 5){
        let value = element + ',';
        document.getElementById(element).className = 'hobbieElementActive';
        hobbies[currentNumberOfHobbies] = value;
        currentNumberOfHobbies++;
    }
    let result = "";
    for(let i = 0; i < currentNumberOfHobbies; i++ ){
        result += hobbies[i];
    }
    document.querySelector('.hobbieButton').setAttribute('value', result);
    console.log(document.querySelector('.hobbieButton').getAttribute('value'));
}

function toggleSex(curr){
    if(curr == 'Male'){
        document.getElementById("MaleSex").style.background = "#FFF";
        document.getElementById("FemaleSex").style.background = 'none';
        document.querySelector('.genderInput').setAttribute('value','male');
        console.log(document.querySelector('.genderInput').attributes);
    }else{
        document.getElementById("FemaleSex").style.background = "#FFF";
        document.getElementById("MaleSex").style.background = 'none';
        document.querySelector('.genderInput').setAttribute('value','female');
    }
}

function removeHobbie(element){
    let i;
    element += ',';
    for( i = 0; i < hobbies.length; i++){
        if(hobbies[i] == element){
            hobbies[i] = "";
            break;
        }
    }
    for(; i < hobbies.length - 1; i++){
        hobbies[i] = hobbies[i + 1];
    }
    hobbies[hobbies.length - 1] = "";
    currentNumberOfHobbies--;
}


function changeDate(id, number){
    document.getElementById(id).textContent = number;
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
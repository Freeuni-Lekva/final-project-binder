function toggleModal(modalId){
    let currModal = document.getElementById(modalId);
    if(currModal.style.display == 'none'|| currModal.style.display == "" ) currModal.style.display = "flex"
    else currModal.style.display = "none";
}

window.onclick = function (event){
    if(event.target == document.getElementById('CompleteRegistrationModal'))
        document.getElementById('CompleteRegistrationModal').style.display = 'none';
}

function toggleDropDown(dropDownId){
    let currDrop = document.getElementById(dropDownId);
    if(currDrop.style.display == 'none' || currDrop.style.display == "") currDrop.style.display = "block";
    else currDrop.style.display = "none";
}

function changeDate(id, number){

    document.getElementById(id).textContent = number;
    if(id === 'userDay') document.getElementById('dayDropDown').style.display = "none";
    else document.getElementById('MonthDropDown').style.display = "none";
}

function setDate(){
    let date = "";
    date+= document.getElementById('userDay').textContent+ "/";
    date+= document.getElementById('userMonth').textContent + "/";
    date+= document.querySelector('.elementYear').value.toString();
    document.getElementById('dateData').value = date;

}
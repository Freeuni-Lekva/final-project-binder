function toggleModal(modalId){
    let currModal = document.getElementById(modalId);
    if(currModal.style.display == 'none'|| currModal.style.display == "" ) currModal.style.display = "flex"
    else currModal.style.display = "none";
}

window.onclick = function (event){
    if(event.target == document.getElementById('CompleteRegistrationModal'))
        document.getElementById('CompleteRegistrationModal').style.display = 'none';
}
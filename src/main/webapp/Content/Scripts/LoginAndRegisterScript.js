function init(){
    console.log("blablalala");
    if(document.querySelector('.servletMessageLogin').textContent != null)
        document.getElementById("LoginModal").style.display = 'block';
    else if(document.querySelector('.servletRegisterMessage').textContent !=null &&
        document.querySelector('.servletRegisterMessage').textContent!== "")
            document.getElementById('RegisterModal').style.display = 'block';
}

init();

function toggleVisibility(inputId, eyeId){
     let currPassword = document.getElementById(inputId);
     let currEye = document.getElementById(eyeId);
    console.log(currPassword + " " + currEye);
     if(currPassword.getAttribute('type') === 'Password'){
         currPassword.setAttribute('type', 'text');
         currEye.setAttribute('class', "fas fa-eye-slash eyeIcon");
     }else{
         currPassword.setAttribute('type', 'Password');
         currEye.setAttribute('class', "fas fa-eye");
     }

}

function toggleModal(modalId){
    let currModal = document.getElementById(modalId);
    console.log("displat aris", currModal.style.display);
    if(currModal.style.display == 'none'|| currModal.style.display == "" ) currModal.style.display = "block"
    else currModal.style.display = "none";
}
window.onclick = function (event){
    if(event.target == document.getElementById('LoginModal'))
        document.getElementById("LoginModal").style.display = 'none';
    else if(event.target == document.getElementById('RegisterModal'))
        document.getElementById('RegisterModal').style.display = 'none';
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
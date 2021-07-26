function checkMessages(){
    console.log(document.querySelector('.servletMessageLogin').textContent);
    if(document.querySelector('.servletMessageLogin').textContent != 'null'){
        document.getElementById("LoginModal").style.display = 'block';
        document.querySelector('.servletMessageLogin').style.display ='block'
    }
    else if(document.querySelector('.servletRegisterMessage').textContent != 'null'){
        document.getElementById('RegisterModal').style.display = 'block';
        document.querySelector('.servletRegisterMessage').style.display ='block';
    }
}
function init(){
    setTimeout(checkMessages, 200);
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
    console.log("display aris", currModal.style.display);
    if(currModal.style.display == ""  || currModal.style.display == 'none') currModal.style.display = "block"
    else currModal.style.display = "none";
}
window.onclick = function (event) {
    if (event.target == document.getElementById('LoginModal')) {
        document.getElementById("LoginModal").style.display = 'none';
    } else if (event.target == document.getElementById('RegisterModal')){
        document.getElementById('RegisterModal').style.display = 'none';
    } else if(event.target == document.getElementById("DontHaveAnAccount")) {
        document.getElementById('RegisterModal').style.display = 'none';
    }
}


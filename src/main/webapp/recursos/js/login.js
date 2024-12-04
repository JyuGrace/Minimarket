window.onload = function() {
    const errorMessage = document.getElementById('login-error-message');
    if (errorMessage) {
        setTimeout(function() {
            errorMessage.style.display = 'none';
        }, 3000); 
    }
};



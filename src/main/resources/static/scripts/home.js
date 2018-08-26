function displayMoreOptions() {
    var checkMoreOptions = document.getElementById("checkMoreOptions");
    var droplist = document.getElementById("selexpidate");
    var expText = document.getElementById("expText");
    var passText = document.getElementById("passwordText");
    var checkBoxPassword = document.getElementById("checkBoxPassword");
    var passwordInput = document.getElementById("passwordInput");
    var showPasswordCheckbox = document.getElementById("showPasswordCheckBox");


    if (checkMoreOptions.checked === true) {
        expText.style.display = "inline-block";
        droplist.style.display = "inline-block";
        passText.style.display = "inline-block";
        checkBoxPassword.style.display = "inline-block";
    } else {
        droplist.style.display = "none";
        expText.style.display = "none";
        droplist.selectedIndex = 3;
        passText.style.display = "none";
        checkBoxPassword.style.display = "none";
        checkBoxPassword.checked = false;
        passwordInput.style.display = "none";
        passwordInput.value = "";
        showPasswordCheckbox.checked = false;
        showPasswordCheckbox.style.display = "none";
    }
}

function showPasswordField() {
    var checkPassword = document.getElementById("checkBoxPassword");
    var passwordInput = document.getElementById("passwordInput");
    var showPasswordCheckbox = document.getElementById("showPasswordCheckBox");


    if (checkPassword.checked === true) {
        passwordInput.style.display = "inline-block";
        showPasswordCheckbox.style.display = "inline-block";
    } else {
        passwordInput.style.display = "none";
        passwordInput.value = "";
        showPasswordCheckbox.style.display = "none";
        showPasswordCheckbox.checked = false;
        passwordInput.type = "password";
    }
}

function showPass() {
    var showPasswordCheckbox = document.getElementById("showPasswordCheckBox");
    var passwordInput = document.getElementById("passwordInput");

    if (showPasswordCheckbox.checked === true) {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}

function clear() {
    var checkMoreOptions = document.getElementById("checkMoreOptions");
    var droplist = document.getElementById("selexpidate");
    var expText = document.getElementById("expText");
    var passText = document.getElementById("passwordText");
    var checkPassword = document.getElementById("checkBoxPassword");
    var passwordInput = document.getElementById("passwordInput");

    checkMoreOptions.checked = false;
    droplist.selectedIndex = 3;
    expText.style.display = "none";
    passText.style.display = "none";
    checkPassword.checked = false;
    passwordInput.value = "";


}

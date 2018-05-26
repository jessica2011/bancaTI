function begin() {
  setTimeout(redirect, 3000);
}
    
function redirect() {
  $(location).attr('href', 'views/login.html');
}
    
$(document).ready(begin);
    
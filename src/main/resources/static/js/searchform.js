var searchform = document.getElementById('search_form');

searchform.addEventListener('focus', function(){
  document.querySelector('.search_cont').style.display = "block";
});

searchform.addEventListener('blur', function(){
  document.querySelector('.search_cont').style.display = "none";
});
//================== 슬라이드 배너 스크립트 코드 =====================

// 슬라이드 인덱스 선언
var slideIndex = 0;
// 슬라이드 보여주기
showSlides(slideIndex);
// 좌우 버튼 누르면 이전/다음 슬라이드 넘기게 해주는 기능
function plusSlides(n) {
  showSlides(slideIndex += n);
}
// 밑에 점 버튼 누르면 지정한 슬라이드로 넘기게 해주는 기능
function currentSlide(n) {
  showSlides(slideIndex = n);
}
// 슬라이드 보여주는 함수
function showSlides(n) {
  // 반복 변수 i 선언
  var i;
  // 슬라이드 사진
  var slides = document.getElementsByClassName("slide");
  // 슬라이드 지정하는 하단 점 모양 버튼
  var dots = document.getElementsByClassName("dot");
  // 인덱스가 슬라이드 전체 길이보다 커지면 처음 슬라이드(0번 인덱스 슬라이드)로 보냄
  if (n > slides.length - 1) {
    slideIndex = 0;
  }
  // 인덱스가 슬라이드 0보다 작아지면 마지막 슬라이드(최댓값 인덱스 슬라이드)로 보냄
  if (n < 0) {
    slideIndex = slides.length - 1;
  }
  // 모든 슬라이드가 안 보이도록 변경
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }

  // 모든 점 버튼들이 비활성화
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  // 현재 지정된 인덱스에 해당하는 슬라이드가 보이게 함
  slides[slideIndex].style.display = "block";
  // 현재 지정된 인덱스에 해당하는 점 버튼이 활성화 상태로 되게 함
  dots[slideIndex].className += " active";
}


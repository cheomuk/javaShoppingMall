// ========= 고정 버튼 자바 스크립트 ===========

  // 페이지 상단에 있으면 top 버튼이 사라지고 페이지 하단으로 내려가면 top 버튼이 생김
  $(function() { 
    $(window).scroll(function() {
      if ($(this).scrollTop() > 450) {
        $('.top_button').fadeIn();
      }
      else {
        $('.top_button').fadeOut();
      }
    });
  
  // top 버튼을 누르면 페이지 최상단으로 스크롤을 자동으로 올림
    $('.top_button').click(function() {
      $('html, body').animate({
        scrollTop : 0
      }, 400);
      return false;
    });
  });

  
const openButton = document.getElementsByClassName("product_img");
const modal = document.querySelector(".product_box");
const overlay = modal.querySelector(".product_overlay");
const closeBtn = modal.querySelector(".product_outb");

// 모달 열기 이벤트 메서드
const openModal = () => {
    modal.classList.remove("product_hidden");
} 
// 모달 닫기 이벤트 메서드
const closeModal = () => { 
    modal.classList.add("product_hidden");
}  
// 검은배경 클릭시 모달 닫기
overlay.addEventListener("click", closeModal);
// 닫기 버튼 클릭시 모달 닫기
closeBtn.addEventListener("click", closeModal);

// 사진에 전부 모달 여는 이벤트 달기
for (var i = 0; i < openButton.length; i++ ){
    openButton.item(i).addEventListener("click", openModal);
}

// 사진에 맞는 모달창 열기

$(document).ready(function(){
  $('.product_img').click(function(){
    modal.classList.remove("product_hidden");
    document.querySelector(".product_box .product_info img").src = $(this).children('img').attr("src");
    document.querySelector(".product_box .info_ment .info_name").innerText 
    = $(this).siblings('figcaption').children('span').filter(':first-child').text();
    document.querySelector(".product_box .info_ment .info_explain").innerText 
    = $(this).siblings('figcaption').children('span').filter(':first-child').siblings('span').text();
    document.querySelector(".product_box .info_ment .info_size").innerText
    = $(this).siblings('.item_src').children('span').text();
  });
});
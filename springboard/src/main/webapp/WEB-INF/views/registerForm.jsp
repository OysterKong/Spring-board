<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<script><src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    
    <style>
     /*    * { box-sizing:border-box; }
        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }
        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }
        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }
        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        .sns-chk {
            margin-top : 5px; 
        }
   div.login_pw{
    position: relative;/* 
    padding: 20px ; */
}
   div.login_pw i{
    position: absolute;
    left: 40%;
    top: 40px;
    color: orange;
}
    </style>
    <title>회원정보등록</title>
</head>
<body>
<div id="menu">
	<ul>
		<li id="logo">OysterBoard</li>
		<li><a href="<c:url value='/'/>">Home</a></li>
		<li><a href="<c:url value='/board/list'/>">Board</a></li>
		<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
		<li><a href="<c:url value='/register/add'/>">Sign in</a></li>
		<li><a href=""><i class="fa fa-search"></i></a></li>
	</ul>
</div>
   <%-- <form action="<c:url value="/register/save"/>" method="post" onsubmit="return formCheck(this)"> --%>
<div>
   <form action="memberAdd" method="post" id="signup">
    <div class="title">
    <h2>회원가입</h2>
    </div>
    <div id="msg" class="msg">
   	    <c:if test="${not empty param.msg}">
	        <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg, "utf-8")}</i>            
	    </c:if>
    </div> 
    <div class="login">
    	<h4>아이디</h4>
    	<input type="text" name="userid" id="userid" placeholder="ID">
    	<span id="result"></span>
    </div>
    <div class="login_pw">
    	<h4>비밀번호</h4>
    	<input type="password" name="passwd" id="passwd" placeholder="Password">
    	<i class="fa fa-eye fa-lg"></i>
    	<h6 style="color:lightgray">비밀번호는 4자리~10자리 사이로 설정이 가능합니다.</h6>
    </div>
    <div class="login_pw">
    	<h4>비밀번호 확인</h4>
    	<input type="password" name="passwd2" id="passwd2" placeholder="Retype Password">
    	<i class="fa fa-eye fa-lg"></i>
    	<span id="result2"></span>
    </div>
    <div class="username">
    	<h4>이름</h4>
    	<input type="text" name="username" id="username" placeholder="Name">
    	<h6 style="color:lightgray">이름은 두글자~다섯글자 사이로 설정이 가능합니다.</h6>
    </div>
    <div class="post" style="display:inline;">
    	<h4>우편번호</h4>
    	<input type="text" name="post" id="sample6_postcode" placeholder="PostCode" numberOnly>
    	<input type="button" onclick="sample6_execDaumPostCode()" value="우편번호 찾기">
    </div>
    <div class="address">
    	<h4>주소</h4>
    	<input type="text" name="addr1" id="sample6_address" placeholder="주소"><br>
    	<input type="text" name="addr2" id="sample6_detailAddress" placeholder="상세주소"><br>
    	<input type="text" name="addr3" id="sample6_extraAddress" placeholder="참고항목">
    	<span id="guide" style="color:#999"></span>
    </div>
    <div class="phone">
    	<h4>전화번호</h4>
    	<select name="phone1" id="phone1">
    		<option value="010">010</option>
    		<option value="011">011</option>
    	</select>&nbsp;-&nbsp;
    	<input type="text" name="phone2" id="phone2" numberOnly>&nbsp;
    	-&nbsp;<input type="text" name="phone3" id="phone3" numberOnly>
    </div>
    <div class="email">
    	<h4>이메일</h4>
    	<input type="text" name="email1" id="email1">@
    	<input type="text" name="email2" id="email2" placeholder="직접입력">
    	<select id="emailSelect">
    		<option value="naver.com">naver.com</option>
    		<option value="daum.net">daum.net</option>
    	</select>
    </div>
    <div class="birthday">
    	<h4>생년월일</h4>
    	<input type="text" name="birth" id="birth" placeholder="year/month/day">
    </div>
    <div class="sns-chk">
    	<h4>Sns</h4>
    	<label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
    </div>
    
    <div class="submit">
    	<input type="submit" value="가입">
    	<input type="reset" value="취소" onClick="location.href='/kong/';">
    </div>
    
	
<!--     <button>회원 가입</button> -->
    
   </form>
</div> 



<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
   //숫자가 아니면 입력창에 입력이 안되게끔 처리 (NumberOnly)
   $(document).ready(function(){
      $('.login_pw i').on('click',function(){
           $('input').toggleClass('active');
           if($('input').hasClass('active')){
               $(this).attr('class',"fa fa-eye-slash fa-lg")
               .prev('input').attr('type',"text");
           }else{
               $(this).attr('class',"fa fa-eye fa-lg")
               .prev('input').attr('type','password');
           }
       });
      
      $("input:text[numberOnly]").on("keyup", function() {
         $(this).val($(this).val().replace(/[^0-9]/g,""));
      }); 
   }); //end. ready

   $(document).ready(function(){
     $("#userid").on("keyup",function(){
        $.ajax({
          url:'idDuplicateCheck',
          type:'get',
          data:{
             id:$("#userid").val(),
          },
          dataType:"text",
          success:function(data,status,xhr){
             console.log(data);
             $("#result").text(data);
          },
          error:function(xhr,status,error){}
        });//end ajax
     });//end on
     
     $('#signup').submit(function() {
          console.log($('#result').text());         // 아이디 중복 검사
         if ($('#result').text() == "아이디 중복") {
               alert('아이디 중복');
               $('#userid').focus();
               return false;
           }
         
         // 비밀번호 확인 불일치
         if ($('#passwd').val() != $('#passwd2').val()) {
               alert('비밀번호 불일치');
               $('#passwd').focus();
               return false;
           }
         
         // 비밀번호 길이 예외처리
         if ($('#passwd').val().length < 4 || $('#passwd').val().length > 10) {
            alert('비밀번호는 4자리~10자리 사이로 설정해야 합니다.')
            $('#passwd').focus();
            return false;
         }
        
         // 빈칸 누락 예외처리
           if ($('#userid').val() == '') {
               alert('아이디를 입력하세요');
               $('#userid').focus();
               return false;
           }
           if ($('#passwd').val() == '') {
               alert('비밀번호를 입력하세요.');
               $('#passwd').focus();
               return false;
           }
           if ($('#passwd2').val() == '') {
               alert('비밀번호 확인란을 입력하세요.');
               $('#passwd2').focus();
               return false;
           }
           if ($('#username').val() == '') {
               alert('이름을 입력하세요.');
               $('#username').focus();
               return false;
           }
           
           if ($('#sample4_postcode').val() == '') {
               alert('우편번호 입력하세요.');
               $('#sample4_postcode').focus();
               return false;
           }
           if ($('#sample4_roadAddress').val() == '') {
               alert('도로명 주소를 입력하세요.');
               $('#sample4_roadAddress').focus();
               return false;
           }
           if ($('#sample4_jibunAddress').val() == '') {
               alert('상세 주소를 입력하세요.');
               $('#sample4_jibunAddress').focus();
               return false;
           }
           if ($('#phone1').val() == '') {
               alert('전화번호를 입력하세요.');
               $('#phone1').focus();
               return false;
           }
           if ($('#phone1').val().length != 3 ) {
               alert('전화번호를 확인하세요.');
               $('#phone1').focus();
               return false;
           }
           
           if ($('#phone2').val() == '') {
               alert('전화번호를 입력하세요.');
               $('#phone2').focus();
               return false;
           }
           if ($('#phone2').val().length > 4 ) {
               alert('전화번호를 확인하세요.');
               $('#phone2').focus();
               return false;
           }

           if ($('#phone3').val() == '') {
               alert('전화번호를 입력하세요.');
               $('#phone3').focus();
               return false;
           }
           if ($('#phone3').val().length > 4 ) {
               alert('전화번호를 확인하세요.');
               $('#phone3').focus();
               return false;
           }

           if ($('#email1').val() == '') {
               alert('이메일을 입력하세요.');
               $('#email1').focus();
               return false;
           }
           if ($('#email2').val() == '') {
               alert('이메일을 입력하세요.');
               $('#email2').focus();
               return false;
           }
           
           // 이름 길이 예외처리
         if ($('#username').val().length < 2 || $('#username').val().length > 5) {
               alert('이름은 두글자에서 다섯글자까지만 설정 가능합니다.');
               $('#username').focus();
               return false;
           }
       });
      
   // 이메일 선택 입력
     $("#emailSelect").on("click", function () {
      $("#email2").val($("#emailSelect").val());
   })
   
         
     
   });//end ready
   
   
   
   
   
/*    <script>
       function formCheck(frm) {
            let msg ='';
            if(frm.id.value.length<3) {
                setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
                return false;
            }
            if(frm.pwd.value.length<3) {
                setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
                return false;
            }           
           
           return true;
       }
       function setMessage(msg, element){
            document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
            if(element) {
                element.select();
            }
       } */
       
       function sample6_execDaumPostcode() {
           new daum.Postcode({
               oncomplete: function(data) {
                   // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                   // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                   // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                   var addr = ''; // 주소 변수
                   var extraAddr = ''; // 참고항목 변수

                   //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                   if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                       addr = data.roadAddress;
                   } else { // 사용자가 지번 주소를 선택했을 경우(J)
                       addr = data.jibunAddress;
                   }

                   // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                   if(data.userSelectedType === 'R'){
                       // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                       // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                       if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                           extraAddr += data.bname;
                       }
                       // 건물명이 있고, 공동주택일 경우 추가한다.
                       if(data.buildingName !== '' && data.apartment === 'Y'){
                           extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                       }
                       // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                       if(extraAddr !== ''){
                           extraAddr = ' (' + extraAddr + ')';
                       }
                       // 조합된 참고항목을 해당 필드에 넣는다.
                       document.getElementById("sample6_extraAddress").value = extraAddr;
                   
                   } else {
                       document.getElementById("sample6_extraAddress").value = '';
                   }

                   // 우편번호와 주소 정보를 해당 필드에 넣는다.
                   document.getElementById('sample6_postcode').value = data.zonecode;
                   document.getElementById("sample6_address").value = addr;
                   // 커서를 상세주소 필드로 이동한다.
                   document.getElementById("sample6_detailAddress").focus();
               }
           }).open();
       }
       
       
   </script>
</body>
</html>
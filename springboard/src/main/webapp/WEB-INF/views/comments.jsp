<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>Comments</title>
    <style>
* {
    border : 0;
    padding : 0;
}

ul {
    border:  1px solid rgb(235,236,239);
    border-bottom : 0;
}

li {
    background-color: #f9f9fa;
    list-style-type: none;
    border-bottom : 1px solid rgb(235,236,239);
    padding : 18px 18px 0 18px;
}

#commentList {
    width : 50%;
    margin : auto;
}

.comment-content {
    overflow-wrap: break-word;
}

.comment-bottom {
    font-size:9pt;
    color : rgb(97,97,97);
    padding: 8px 0 8px 0;
}

.comment-bottom > a {
    color : rgb(97,97,97);
    text-decoration: none;
    margin : 0 6px 0 0;
}

.comment-area {
    padding : 0 0 0 46px;
}

.commenter {
    font-size:12pt;
    font-weight:bold;
}

.commenter-writebox {
    padding : 15px 20px 20px 20px;
}

.comment-img {
    font-size:36px;
    position: absolute;
}

.comment-item {
    position:relative;
}

.up_date {
    margin : 0 8px 0 0;
}

#comment-writebox {
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
}

textarea {
    display: block;
    width: 100%;
    min-height: 17px;
    padding: 0 20px;
    border: 0;
    outline: 0;
    font-size: 13px;
    resize: none;
    box-sizing: border-box;
    background: transparent;
    overflow-wrap: break-word;
    overflow-x: hidden;
    overflow-y: auto;
}

#comment-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}

.btn {
    font-size:10pt;
    color : black;
    background-color: #eff0f2;
    text-decoration: none;
    padding : 9px 10px 9px 10px;
    border-radius: 5px;
    float : right;
}

#btn-write-comment, #btn-write-reply { 
    color : #009f47;
    background-color: #e0f8eb;
}

#btn-cancel-reply { 
    background-color: #eff0f2;
    margin-right : 10px;
}

#btn-write-modify { 
    color : #009f47;
    background-color: #e0f8eb;
}

#btn-cancel-modify { 
    margin-right : 10px;
}

#reply-writebox {
    display : none;
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
    margin : 10px;
}

#reply-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}

#modify-writebox {
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
    margin : 10px;
}

#modify-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}



.paging {
    color: black;
    width: 100%;
    text-align: center;
}

.page {
    color: black;
    text-decoration: none;
    padding: 6px;
    margin-right: 10px;
}

.paging-active {
    background-color: rgb(216, 216, 216);
    border-radius: 5px;
    color: rgb(24, 24, 24);
}

.paging-container {
    width:100%;
    height: 70px;
    margin-top: 50px;
    margin : auto;
}
    </style>
</head>
<body>
<h2>comments</h2>
comment: <input type="text" name="comment"><br>
<button id="sendBtn" type="button">SEND</button>
<button id="modBtn" type="button">??????</button>
    
<div id="commentList"></div>
<div id="replyForm" style="display:none">
	<input type="text" name="replyComment">
	<button id="wrtRepBtn" type="button">??????</button>
</div>
<script>
    	
let bno = 426;
    
let showList = function(bno){
      $.ajax({
          type:'GET',       // ?????? ?????????
          url: '/kong/comments?bno='+bno,  // ?????? URI
          success : function(result){
              $("#commentList").html(toHtml(result));    // ??????????????? ????????? ???????????? ????????? ??????
          },
          error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
      }); // $.ajax()
}

    
  $(document).ready(function(){
      showList(bno);
      
	  $("#modBtn").click(function(){
	  		 let cno = $(this).attr("data-cno");
	    	 let comment = $("input[name=comment]").val();
	    	 
	    	 if(comment.trim()==''){
	    		 alert("????????? ??????????????????.");
	    		 $("input[name=comment]").focus()
	    		 return;
	    	 }
	    	 
	    	 $.ajax({
	             type:'PATCH',       // ?????? ?????????
	             url: '/kong/comments/'+cno,  // ?????? URI
	             headers : { "content-type": "application/json"}, // ?????? ??????
	             data : JSON.stringify({cno:cno, comment:comment}),  // ????????? ????????? ?????????. stringify()??? ????????? ??????.
	             success : function(result){
	            	alert(result);
	            	showList(bno);
	             },
	             error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
	         }); // $.ajax()
	      });
	  
	  
	  $("#wrtRepBtn").click(function(){
	    	 let comment = $("input[name=replyComment]").val();
	    	 let pcno = $("#replyForm").parent().attr("data-pcno");
	    	 
	    	 if(comment.trim()==''){
	    		 alert("????????? ??????????????????.");
	    		 $("input[name=replyComment]").focus()
	    		 return;
	    	 }
	    	 
	    	 $.ajax({
	             type:'POST',       // ?????? ?????????
	             url: '/kong/comments?bno='+bno,  // ?????? URI
	             headers : { "content-type": "application/json"}, // ?????? ??????
	             data : JSON.stringify({pcno:pcno, bno:bno, comment:comment}),  // ????????? ????????? ?????????. stringify()??? ????????? ??????.
	             success : function(result){
	            	alert(result);
	            	showList(bno);
	             },
	             error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
	         }); // $.ajax()
	         
	         $("#replyForm").css("display", "none")
	         $("input[name=replyComment]").val('')
	         $("#replyForm").appendTo("body");
	      });
	  
      
      
	  $("#sendBtn").click(function(){
    	 let comment = $("input[name=comment]").val();
    	 
    	 if(comment.trim()==''){
    		 alert("????????? ??????????????????.");
    		 $("input[name=comment]").focus()
    		 return;
    	 }
    	 
    	 $.ajax({
             type:'POST',       // ?????? ?????????
             url: '/kong/comments?bno='+bno,  // ?????? URI
             headers : { "content-type": "application/json"}, // ?????? ??????
             data : JSON.stringify({bno:bno, comment:comment}),  // ????????? ????????? ?????????. stringify()??? ????????? ??????.
             success : function(result){
            	alert(result);
            	showList(bno);
             },
             error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
         }); // $.ajax()
      });
	  
	 $("#commentList").on("click", ".modBtn", function(){
	     let cno = $(this).parent().attr("data-cno");
	     let comment = $("span.comment", $(this).parent()).text();
	  
	     // 1. comment??? ????????? input??? ????????????
	     $("input[name=comment]").val(comment);
	     // 2. cno????????????
	     $("#modBtn").attr("data-cno", cno);
	 });
	 
     $("#commentList").on("click", ".replyBtn", function(){
    	 // 1. replyForm??? ?????????
    	 $("#replyForm").appendTo($(this).parent());
    	 
    	 // 2. ????????? ????????? ?????? ????????????,
    	 $("#replyForm").css("display", "block");
     
     });
	 
     
    /*  $(".delBtn").click(function(){ */
     $("#commentList").on("click", ".delBtn", function(){
         let cno = $(this).parent().attr("data-cno");
         let bno = $(this).parent().attr("data-bno");
    	 
    	 $.ajax({
             type:'DELETE',       // ?????? ?????????
             url: '/kong/comments/'+cno+'?bno='+bno,  // ?????? URI
             success : function(result){
                alert(result)
                showList(bno);
             },
             error   : function(){ alert("error") } // ????????? ???????????? ???, ????????? ??????
         }); // $.ajax()
      });
     
});
        
let toHtml = function(comments) {
	let tmp = "<ul>";
	
	comments.forEach(function(comment){
		tmp += '<li data-cno='+ comment.cno
		tmp += ' data-pcno='+ comment.pcno
		tmp += ' data-bno='+ comment.bno + '>'
		if(comment.cno!=comment.pcno)
		tmp += '???'
		tmp += ' ???????????????=<span class="commenter">' + comment.commenter + '</span>'
		tmp += ' ????????????=<span class="comment">' + comment.comment + '</span>'
		tmp += ' ?????????='+comment.up_date
		tmp += '<button class="delBtn">??????</button>'
		tmp += '<button class="modBtn">??????</button>'
		tmp += '<button class="replyBtn">??????</button>'
		tmp += '</li>'	
	})
	
	return tmp + "</ul>";
}        


let id = 'asdf';

let addZero = function(value=1){
    return value > 9 ? value : "0"+value;
}

let dateToString = function(ms=0) {
    let date = new Date(ms);

    let yyyy = date.getFullYear();
    let mm = addZero(date.getMonth() + 1);
    let dd = addZero(date.getDate());

    let HH = addZero(date.getHours());
    let MM = addZero(date.getMinutes());
    let ss = addZero(date.getSeconds());          

    return yyyy+"."+mm+"."+dd+ " " + HH + ":" + MM + ":" + ss;            
}

$(document).ready(function(){
    $("a.btn-write").click(function(e){
        let target = e.target;
        let cno = target.getAttribute("data-cno")
        let bno = target.getAttribute("data-bno")

        let repForm = $("#reply-writebox");
        repForm.appendTo($("li[data-cno="+cno+"]"));
        repForm.css("display", "block");
        repForm.attr("data-pcno", pcno);
        repForm.attr("data-bno",  bno);
    });

    $("#btn-cancel-reply").click(function(e){
        $("#reply-writebox").css("display", "none");
    });

    $("a.btn-modify").click(function(e){
        let target = e.target;
        let cno = target.getAttribute("data-cno");
        let bno = target.getAttribute("data-bno");
        let pcno = target.getAttribute("data-pcno");
        let li = $("li[data-cno="+cno+"]");
        let commenter = $(".commenter", li).first().text();
        let comment = $(".comment-content", li).first().text();

        $("#modalWin .commenter").text(commenter);
        $("#modalWin textarea").text(comment);
        $("#btn-write-modify").attr("data-cno", cno);
        $("#btn-write-modify").attr("data-pcno", pcno);
        $("#btn-write-modify").attr("data-bno", bno);

        // ???????????? ?????? ????????? ????????????.
        $("#modalWin").css("display","block");
    });

    $("a.btn-delete").click(function(e){
        alert("delete");
    });

    $("#btn-write-modify").click(function(){
        // 1. ????????? ????????? ????????? ??????
        // 2. ?????? ?????? ?????????. 
        $(".close").trigger("click");
    });

    $(".close").click(function(){
        $("#modalWin").css("display","none");
    });
});
        
        
        
        
        
    </script>
</body>
</html>
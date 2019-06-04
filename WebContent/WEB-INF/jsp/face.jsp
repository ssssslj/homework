<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	.outer .inner{
		
	}
	.outer{
		width: 520px;
		height: 460px;
		position: relative;
		text-align: center;
		
	}
	.inner{
		width: 520px;
		height: 400px;	
		position: absolute;
		top:0px;
	}
	.kuang1{
		margin: 50px 0 0 100px;
		float:left;
	}
	.kuang2{
		margin: 50px 100px 0 0 ;
		float:right;
	}
	video{
		margin-left:10px;
	}
	canvas{
		margin-left:10px;
	}
	.inner .img{
		width:100%
	} 
	.btn{
		margin-top:20px;
	}
</style>
</head>

<body style="background:url(${pageContext.request.contextPath }/assets/image/bg.jpg);background-size:100%">
	<form action="face_compare" method="post">
	<div class="outer kuang1">
		<video id="video" width="500px" height="400px" autoplay="autoplay"></video>
		<img class="btn" height="40px" src="${pageContext.request.contextPath }/assets/image/kaiqi.png" onclick="getMedia()">
		<!-- <input type="button" title="开启摄像头"  value="开启摄像头" onclick="getMedia()" /> -->
		<div class="inner">
			<img class="img" alt="" src="${pageContext.request.contextPath }/assets/image/kuang.png">
			
		</div>
	</div>
	<div class="outer kuang2">
		<canvas id="canvas" width="500px" height="400px"></canvas>
		
		<!-- <button type="button" id="snap" onclick="takePhoto()">拍照</button>-->
		<input type="hidden" id="isNecessary" name="imgstr" />
		<input type="hidden" id="isNecessary" name="number" value="<%=request.getSession().getAttribute("num") %>"/>
		
		<div class="inner">
			<img class="img" alt="" src="${pageContext.request.contextPath }/assets/image/kuang.png">
		</div>
		<div style="margin:0 auto;width: 300px;height: 30px;" >
			<img height="40px" class="btn" style="float:left" src="${pageContext.request.contextPath }/assets/image/paizhao.png" onclick="takePhoto()">
			<input type="submit" class="btn" value="" style="background:url(${pageContext.request.contextPath }/assets/image/shangchuan.png);background-size:100%;width:150px; height:40px; border:0; padding:0;" >
		</div>
	</div>
	
	
	</form>

	<script>
        function getMedia() {
            let constraints = {
                video: {width: 500, height: 500},
                audio: true
            };
            //获得video摄像头区域
            let video = document.getElementById("video");
            let promise = navigator.mediaDevices.getUserMedia(constraints);
            promise.then(function (MediaStream) {
                video.srcObject = MediaStream;
                video.play();
            });
        }
 
      function takePhoto() {
      //获得Canvas对象
      let video = document.getElementById("video");
      let canvas = document.getElementById("canvas");
      let ctx = canvas.getContext('2d');
      
      ctx.drawImage(video, 0, 0, 500, 500);
      document.getElementById('isNecessary').value=canvas.toDataURL("image/png");
      }
      
</script>
</body>
</html>
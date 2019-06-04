<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	</div>
</div>
	<script src="${pageContext.request.contextPath }/assets/js/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/assets/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/assets/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/assets/js/locales/bootstrap-datetimepicker.zh-CN.js" ></script>
<script type="text/javascript">
	function f(str){
		var eaxm_now = document.getElementById(str);
		var dis_v = exam_now.style.display;
		
		if(dis_v == 'block'){
			eaxm_now.style.display = 'none';
			eaxm_now.style.color = 'white';
		}else{
			eaxm_now.style.display ='block';
			eaxm_now.style.color = 'white';
		}
	}
</script>
</body>
</html>
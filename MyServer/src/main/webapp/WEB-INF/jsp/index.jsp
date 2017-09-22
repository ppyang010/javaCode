<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bower_components/jquery/dist/jquery.min.js"></script>
</head>
<body>
hello

<script type="text/javascript">
	/* $.ajax({
        
        url: '${pageContext.request.contextPath}/hello',
        type: "POST",
        timeout: 100000, //超时时间设置，单位毫秒
        
         dataType: "json", 
        data: {
            start:0,
            limit:9,
             bigCategoryId:'562ED8D7-2F4E-4CC2-BC5F-6D33E041C598' ,
             filter:{
                serverName:'123',
                companyName:'123',
                parentCategoryId:'1'
               // childrenCategoryId:'1',
                 lowerPrice:'',
                higherPrice:'' 
            },
            sort:{
                SortName:'',
                SortType:0
            }  
        },
        success: function (result) {
            callback(result);
        },
        error: function (xmlHttpRequest, textStatus, errorThrown) {
           
        }
    }) */
	 var data={
		    "start": "0", 
		    "limit": "9", 
		    "bigCategoryId": "562ED8D7-2F4E-4CC2-BC5F-6D33E041C598", 
		    "filter": {
		        "serverName": "123", 
		        "companyName": "1", 
		        "parentCategoryId": "1"
		    }}; 
	
	 var dataobj={
			    start: "0", 
			    limit: "9", 
			    bigCategoryId: "123456", 
			    filter: {
			        ServerName: "123", 
			        companyName: "1", 
			        parentCategoryId: "1"
			    }}; 
		    
    $.ajax({
        
        url: '${pageContext.request.contextPath}/hello',
        type: "POST",
        timeout: 100000, //超时时间设置，单位毫秒
        contentType:"application/json",
       /*  dataType: "json", */
        data:JSON.stringify(dataobj)	,
        success: function (result) {
            callback(result);
        },
        error: function (xmlHttpRequest, textStatus, errorThrown) {
           
        }
    })
   

</script>
</body>
</html>
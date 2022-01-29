<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 모바일에서 사이트가 PC에서의 픽셀크기 기준으로 작동하게 하기(반응형 하려면 필요) -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- daisy UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@1.25.4/dist/full.css" rel="stylesheet" type="text/css" />
<!-- 테일윈드 cheat sheet -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css" />
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<!-- jquery -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" />
<!-- vue -->
<script src="https://unpkg.com/vue@next"></script>

<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer></script>
<title>안녕</title>
</head>
<body>
  <i class="fas fa-angle-up"></i>
  <br />
  <button class="btn; btn-primary" onclick="hello();">안녕하세요.</button>
  <div class="background-color; bg-blue-100">폰트확인</div>
</body>
</html>
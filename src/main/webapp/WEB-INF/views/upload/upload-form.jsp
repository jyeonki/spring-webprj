<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <!-- jquery -->
    <script src="/js/jquery-3.3.1.min.js"></script>

    <style>

        .fileDrop {
            width: 800px;
            height: 400px;
            border: 1px dashed gray;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 1.5em;
        }
        .uploaded-list {
            display: flex;
        }
        .img-sizing {
            display: block;
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>

    <!-- 파일 업로드를 위한 form - 동기 처리 -->
    <form action="/upload" method="post" enctype="multipart/form-data"> 
        <!-- 파일은 무조건 POST! -->
        <!-- enctype="multipart/form-data" - '내가 지금 보내는 건 파일 형식입니다' 라고 명시해준다 -->
        
        <input type="file" name="file" multiple>
        <!-- mutiple : 파일 여러개 업로드 가능 -->
        
        <button type="submit">업로드</button>
    </form>


    <!-- 파일 업로드를 위한 form - 비동기 처리 -->
    <div class="fileDrop">
        <span>DROP HERE!</span>
    </div>

    <!-- 
        - 파일 정보를 서버로 보내기 위해서는 <input type="file"> 이 필요
        - 해당 input태그는 사용자에게 보여주어 파일을 직접 선택하게 할 것이냐
          혹은 드래그앤 드롭으로만 처리를 할 것이냐에 따라 display를 상태를 결정
    -->

    <div class="uploadDiv">
        <input type="file" name="files" id="ajax-file" style="display:none;">
    </div>


    <!-- 업로드된 이미지의 썸네일을 보여주는 영역 -->
    <div class="uploaded-list">

    </div>



    <script>

        // start JQuery
        // jQuery 즉시 실행 함수
        $(document).ready(function () {

            function isImageFile(originFileName) {
                //정규표현식
                const pattern = /jpg$|gif$|png$/i; // $ : -로 끝나는, i : 대소문자 구별 X
                return originFileName.match(pattern);
            }

             // 파일의 확장자에 따른 렌더링 처리
             function checkExtType(fileName) {

                //원본 파일 명 추출
                let originFileName = fileName.substring(fileName.indexOf("_") + 1);

                //확장자 추출후 이미지인지까지 확인
                if (isImageFile(originFileName)) { // 파일이 이미지라면

                    const $img = document.createElement('img');
                    $img.classList.add('img-sizing');
                    $img.setAttribute('src', '/loadFile?fileName=' + fileName); 
                    $img.setAttribute('alt', originFileName); // 원본 파일 이름

                    $('.uploaded-list').append($img);
                }

                // 이미지가 아니라면 다운로드 링크를 생성
                else {

                    // a태그 생성
                    const $a = document.createElement('a');
                    $a.setAttribute('href', '/loadFile?fileName=' + fileName);

                    // img태그 생성
                    const $img = document.createElement('img');
                    $img.classList.add('img-sizing');
                    $img.setAttribute('src', '/img/file_icon.jpg'); 
                    $img.setAttribute('alt', originFileName); 


                    $a.append($img);
                    $a.innerHTML += '<span>' + originFileName + '</span>';

                    $('.uploaded-list').append($a);
                }
                
            }

             // 드롭한 파일을 화면에 보여주는 함수
             function showFileData(fileNames) {

                // 이미지인지? 이미지가 아닌지에 따라 구분하여 처리
                // 이미지면 썸네일을 렌더링하고 아니면 다운로드 링크를 렌더링한다.
                for (let fileName of fileNames) {
                    checkExtType(fileName);
                }
            }


            // drag & drop 이벤트
            const $dropBox = $('.fileDrop');

             // drag 진입 이벤트
             $dropBox.on('dragover dragenter', e => {
                e.preventDefault(); 
                $dropBox
                    .css('border-color', 'red')
                    .css('background', 'lightgray');
            });

            // drag 탈출 이벤트
            $dropBox.on('dragleave', e => {
                e.preventDefault(); 
                $dropBox
                    .css('border-color', 'gray')
                    .css('background', 'transparent');
            });

            // drop 이벤트
            $dropBox.on('drop', e => {
                e.preventDefault();
                // console.log('drop 이벤트 작동!');

                // 드롭된 파일 정보를 서버로 전송

                // 1. 드롭된 파일 데이터 읽기
                // console.log(e);
                const files = e.originalEvent.dataTransfer.files;
                // console.log('drop file data: ', files);

                // 2. 원본 파일 데이터를 input[type=file]태그에 저장
                const $fileInput = $('#ajax-file');
                $fileInput.prop('files', files);

                // console.log($fileInput);

                // 3. 파일 데이터를 비동기 전송하기 위해서는 FormData 객체가 필요
                const formData = new FormData();

                // 4. 전송할 파일들을 전부 FormData 안에 포장
                for (let file of $fileInput[0].files) {
                    formData.append('files', file); // 'files' 서버에서 읽는 이름, name이랑 맞춰주는게 좋다
                }

                // 5. 비동기 요청 전송
                const reqInfo = {
                    method: 'POST',
                    body: formData
                };
                fetch('/ajax-upload', reqInfo)
                    .then(res => {
                        // console.log(res.status);
                        return res.json();
                    })
                    .then(fileNames => {
                        console.log(fileNames);

                        showFileData(fileNames);
                    })
            })
        }); 



    </script>

</body>
</html>
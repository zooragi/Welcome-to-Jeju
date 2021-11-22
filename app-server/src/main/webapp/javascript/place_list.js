(function(){

    "use strict"

    const qs = x => document.querySelector(x);
    
    const $placesList = qs("#placesList");
		const $placeName = qs("#place_name");
		const $placeAddress = qs("#address_name");
		const $placeComment = qs("#place_comment");
		let placeData = [];
    // 마커를 담을 배열입니다
    let markers = [];

    let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(33.3700, 126.4500), // 지도의 중심좌표
            level: 9 // 지도의 확대 레벨
        };  

    // 지도를 생성합니다    
    let map = new kakao.maps.Map(mapContainer, mapOption); 

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({zIndex:1});


    function mapApi() {
        const regex = /[^0-9]/g;

        function init() {
						getDataFromServer();
            placeListClickEvent();
            moveMapLocationEvent();
        }

				function getDataFromServer(){
					let xhr = new XMLHttpRequest();
					xhr.onreadystatechange = () => {
					  if (xhr.readyState != 4 || xhr.status != 200)
					    return;
					  placeData = JSON.parse(xhr.responseText);
						displayPlaces(placeData);
					};
					xhr.open("GET", "../../app/place/list01", true);
					xhr.send();
				}
				
        // 검색 결과 목록과 마커를 표출하는 함수입니다
        function displayPlaces(places) {

            let listEl = document.getElementById('placesList');
            let menuEl = document.getElementById('menu_wrap');
            let fragment = document.createDocumentFragment();
            let bounds = new kakao.maps.LatLngBounds();
            
            for ( let i=0; i<places.length; i++ ) {

                // 마커를 생성하고 지도에 표시합니다
                let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);
                let marker = addMarker(placePosition, i);
                let itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                bounds.extend(placePosition);

                // 마커와 검색결과 항목에 mouseover 했을때
                // 해당 장소에 인포윈도우에 장소명을 표시합니다
                // mouseout 했을 때는 인포윈도우를 닫습니다
                (function(marker, title) {
                    kakao.maps.event.addListener(marker, 'mouseover', function () {
                        displayInfowindow(marker, title);
                    });

                    kakao.maps.event.addListener(marker, 'mouseout', function() {
                        infowindow.close();
                    });

                    itemEl.onmouseover =  function () {
                        displayInfowindow(marker, title);
                    };

                    itemEl.onmouseout =  function () {
                        infowindow.close();
                    };
                })(marker, places[i].place_name);

                fragment.appendChild(itemEl);
            }

            // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
            listEl.appendChild(fragment);
            menuEl.scrollTop = 0;

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            // map.setBounds(bounds);
        }

        // 검색결과 항목을 Element로 반환하는 함수입니다
        function getListItem(index, places) {
            let el = document.createElement('li'),
            itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                        '<div class="info">' +
                        '   <h5>' + places.place_name + '</h5>';

            if (places.road_address_name) {
                itemStr += '    <span>' + places.road_address_name + '</span>' +
                            '   <span class="jibun gray">' +  places.address_name  + '</span>';
            } else {
                itemStr += '    <span>' +  places.address_name  + '</span>'; 
            }
                        
            
            
            el.innerHTML = itemStr;
            el.className = 'item';

            return el;
        }

        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        function addMarker(position, idx, title) {
            let imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'; // 마커 이미지 url, 스프라이트 이미지를 씁니다
            let imageSize = new kakao.maps.Size(24, 35);  // 마커 이미지의 크기

            let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
            let marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage 
            });

            marker.setMap(map); // 지도 위에 마커를 표출합니다
            markers.push(marker);  // 배열에 생성된 마커를 추가합니다

            return marker;
        }

        // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
        // 인포윈도우에 장소명을 표시합니다
        function displayInfowindow(marker, title) {
            let content = '<div class="marker" style="padding:10px;z-index:1;text-align:center;">' + title + '</div>';

            infowindow.setContent(content);
            infowindow.open(map, marker);
        }

        function placeListClickEvent() {
            $placesList.addEventListener('click', (e) => {
                let liTag = e.target.closest('li'); 
                if (!liTag) return; 
                if (!$placesList.contains(liTag)) return;

								
                let selectedPlaceItemNum = parseInt(liTag.childNodes[0].className.replace(regex, ""));
								$placeName.value = placeData[selectedPlaceItemNum-1].place_name;
								$placeAddress.value = placeData[selectedPlaceItemNum-1].address_name;
								let comments = placeData[selectedPlaceItemNum-1].comments;
								let place_comments = "";
								let count = 1;
								comments.forEach(e => {
									place_comments += `${count++}. ` + e.comment + "\r\n"
								});
								console.log(place_comments);
								$placeComment.innerText = place_comments;
								
								document.querySelector(".modal").style.display = 'block';
								document.getElementsByTagName("BODY")[0].style.overflow = 'hidden';
								document.querySelector(".modal").classList.toggle('show');
								                	
								qs('.place_cancel_btn').addEventListener('click',()=>{
									document.getElementsByTagName("BODY")[0].style.overflow = 'visible';
									document.querySelector(".modal").style.display = 'none';
									document.querySelector(".modal").classList.toggle('show');
								});

            });
        }

        function moveMapLocationEvent() {
            $placesList.addEventListener('mouseover', (e) => {
                let liTag = e.target.closest('li'); 
                if (!liTag) return; 
                if (!$placesList.contains(liTag)) return;

                let selectedPlaceItemNum = parseInt(liTag.childNodes[0].className.replace(regex, ""));
                // 이동할 위도 경도 위치를 생성합니다 
                let moveLatLon = new kakao.maps.LatLng(placeData[selectedPlaceItemNum-1].y, placeData[selectedPlaceItemNum-1].x);

                // 지도 중심을 부드럽게 이동시킵니다
                // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
                map.panTo(moveLatLon);
            });
        }

        init();
    }
    mapApi();
    
})();
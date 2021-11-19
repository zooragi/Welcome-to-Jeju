(function(){
    "use strict"

    const qs = x => document.querySelector(x);

    // Selectors
    const $sideMenu = qs(".side-menu");
    const $sideMenuBar = qs(".side-menu-bar");
    const $sideMenuCloseBtn = qs(".close-button");
    const $slideThemeContent = qs(".slide-theme-content");

    // EventListener
    $sideMenu.addEventListener('click', ()=>$sideMenuBar.style.display = 'flex' );
    $sideMenuCloseBtn.addEventListener('click', ()=>$sideMenuBar.style.display = 'none');

    function executeSlide(){
        let index = 0;
        let prevIndex = 0;
        $slideThemeContent.children[index].classList.add("active");
        setInterval(()=>{
            if(index === $slideThemeContent.children.length) {
                $slideThemeContent.children[0].classList.remove("active");
            }
            if(index > $slideThemeContent.children.length-1) {
                $slideThemeContent.children[index-1].classList.remove("active");
                index=1;
                $slideThemeContent.children[index].classList.add("active");
            } else {
                $slideThemeContent.children[index].classList.remove("active");
                index++;
                if(index === $slideThemeContent.children.length) {
                    $slideThemeContent.children[0].classList.add("active");
                    return;
                };
                $slideThemeContent.children[index].classList.add("active");
            }


        },3000);
    }
    executeSlide();
})();
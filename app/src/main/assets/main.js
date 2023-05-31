//timeline
const tl = gsap.timeline();

tl.from(".main-container .images", {
  y: -100,
  duration: 0.9,
  ease: Power1.easeOut
}).from(".main-container h2",{
    x:-50,
    duration:1.2,
    opacity: 0,
    ease: Elastic.easeOut
}, "-=0.5").from(".main-container p",{
    x:-50,
    duration:1.2,
    opacity: 0,
    ease: Elastic.easeOut
}, "-=0.5");

(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{15:function(e,t,n){},17:function(e,t,n){e.exports=n.p+"static/media/logo.5d5d9eef.svg"},20:function(e,t,n){},22:function(e,t,n){"use strict";n.r(t);var a=n(0),s=n.n(a),o=n(3),c=n.n(o),l=(n(15),n(4)),r=n(5),i=n(7),m=n(6),u=n(8),d=n(1),h=(n(17),n(18),n(20),"http://localhost:8080"),f=function(e){function t(e){var n;return Object(l.a)(this,t),(n=Object(i.a)(this,Object(m.a)(t).call(this,e))).state={question:{id:-1,description:"Loading data..."},userId:void 0,hasAnswered:!1},console.log("state",n.state),n.answerQuestion.bind(Object(d.a)(Object(d.a)(n))),n.getOrSetName.bind(Object(d.a)(Object(d.a)(n))),n.fetchAndSetName.bind(Object(d.a)(Object(d.a)(n))),n}return Object(u.a)(t,e),Object(r.a)(t,[{key:"render",value:function(){var e=this;return this.state.hasAnswered?s.a.createElement("div",{className:"App container"},s.a.createElement("div",{className:"row"},s.a.createElement("div",{className:"col-md-4 offset-md-4 mt-5"},s.a.createElement("header",{className:"App-header"},s.a.createElement("h1",{className:"center-text"},"Julspelet")))),s.a.createElement("div",{className:"row center-text mt-5"},s.a.createElement("div",{className:"col-md-6 offset-md-3"},s.a.createElement("p",{className:"text-monospace"},"Tack f\xf6r ditt svar!"))),s.a.createElement("div",{className:"row mt-5"},s.a.createElement("div",{className:"col-md-6 offset-md-3 center-text"},s.a.createElement("p",null,s.a.createElement("small",null,"Du spelar som ",this.state.userId))))):s.a.createElement("div",{className:"App container"},s.a.createElement("div",{className:"row"},s.a.createElement("div",{className:"col-md-4 offset-md-4 mt-5"},s.a.createElement("header",{className:"App-header"},s.a.createElement("h1",{className:"center-text"},"Julspelet")))),s.a.createElement("div",{className:"row center-text mt-5"},s.a.createElement("div",{className:"col-md-6 offset-md-3"},s.a.createElement("p",{className:"text-monospace"},this.state.question.description))),s.a.createElement("div",{className:"row mt-2"},s.a.createElement("div",{className:"col-md-6 offset-md-3 center-text"},s.a.createElement("button",{className:"btn btn-lg btn-success m-3",onClick:function(){return e.answerQuestion(!0)}},"Sant"),s.a.createElement("button",{className:"btn btn-lg btn-danger",onClick:function(){return e.answerQuestion(!1)}},"Falskt"))),s.a.createElement("div",{className:"row mt-5"},s.a.createElement("div",{className:"col-md-6 offset-md-3 center-text"},s.a.createElement("p",null,s.a.createElement("small",null,"Du spelar som ",this.state.userId)))))}},{key:"answerQuestion",value:function(e){var t=this,n={userId:this.state.userId,answer:e,questionId:this.state.question.id};console.log(n),fetch(h+"/question/validate",{method:"post",headers:{"Content-type":"application/json; charset=UTF-8"},body:JSON.stringify(n)}).then(function(e){t.setState(function(e){return e.hasAnswered=!0,e})}).catch(function(e){console.log("Request failed",e)})}},{key:"getOrSetName",value:function(){var e=this,t=localStorage.getItem("julspelet");console.log("got maybeuser",t),console.log(null==t),"undefined"===t||null==t?(console.log("requesting user"),e.fetchAndSetName().then(function(t){console.log("got user",t),localStorage.setItem("julspelet",t._id),e.setState(function(e){return e.userId=t._id,e})})):(console.log("entered else"),e.setState(function(e){return e.userId=t,e})),console.log("localstorage",t)}},{key:"fetchAndSetName",value:function(){return fetch(h+"/user",{method:"post",headers:{"Content-type":"application/json; charset=UTF-8"},body:""}).then(function(e){return e.json().then(function(e){return console.log("Request succeeded with JSON response",e),e})}).catch(function(e){console.log("Request failed",e)})}},{key:"componentWillMount",value:function(){this.getOrSetName();var e=this;fetch(h+"/question").then(function(t){200===t.status?t.json().then(function(t){e.setState(function(e){return console.log("setting q",t),e.question=t,e})}):console.log("Looks like there was a problem. Status Code: "+t.status)}).catch(function(e){console.log("Fetch Error :-S",e)})}}]),t}(a.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));c.a.render(s.a.createElement(f,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})},9:function(e,t,n){e.exports=n(22)}},[[9,2,1]]]);
//# sourceMappingURL=main.1f799142.chunk.js.map
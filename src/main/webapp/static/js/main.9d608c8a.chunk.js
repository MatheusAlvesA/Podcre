(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{19:function(e,t,a){e.exports=a(47)},24:function(e,t,a){},47:function(e,t,a){"use strict";a.r(t);var o=a(0),s=a.n(o),n=a(8),i=a.n(n),r=(a(24),a(2)),l=a(3),c=a(6),d=a(4),m=a(5),u=a(1),p=function(e){function t(){return Object(r.a)(this,t),Object(c.a)(this,Object(d.a)(t).apply(this,arguments))}return Object(m.a)(t,e),Object(l.a)(t,[{key:"render",value:function(){var e=s.a.createElement("button",{className:"btn btn-outline-success ml-auto d-block","data-toggle":"modal","data-target":"#cadastrarModal"},"Cadastrar");return!0===this.props.logado&&(e=null),s.a.createElement("nav",{className:"navbar navbar-dark bg-dark"},s.a.createElement("h2",{id:"tituloNavBar"},"Podcr\xea",s.a.createElement("small",{className:"text-muted",id:"versionNavbar"}," Alpha")),e)}}]),t}(o.Component),h=window.$,b=function(e){function t(){var e;return Object(r.a)(this,t),(e=Object(c.a)(this,Object(d.a)(t).call(this))).state={nome:"",senha:""},e}return Object(m.a)(t,e),Object(l.a)(t,[{key:"submeter",value:function(){h.ajax({url:"https://podcre-223420.appspot.com/api/Login",data:JSON.stringify(this.state),type:"POST",success:this.sucesso.bind(this),error:this.erro.bind(this)})}},{key:"sucesso",value:function(e){void 0!==this.props.callbackSucesso&&null!==this.props.callbackSucesso&&this.props.callbackSucesso(this.state.nome)}},{key:"erro",value:function(e){h("#mensagemErro").css("display","block"),setTimeout(function(){h("#mensagemErro").css("display","none")},2e3),void 0!==this.props.callbackErro&&null!==this.props.callbackErro&&this.props.callbackErro(e)}},{key:"watcher",value:function(){this.setState({nome:h("#inputNome").val()}),this.setState({senha:h("#inputSenha").val()})}},{key:"render",value:function(){return s.a.createElement("div",null,s.a.createElement("br",null),s.a.createElement("form",{id:"formLogin"},s.a.createElement("div",{className:"form-group"},s.a.createElement("label",{htmlFor:"inputNome"},"Login"),s.a.createElement("input",{type:"text",onChange:this.watcher.bind(this),className:"form-control",id:"inputNome",placeholder:"Nome de usu\xe1rio"})),s.a.createElement("div",{className:"form-group"},s.a.createElement("label",{htmlFor:"inputSenha"},"Senha"),s.a.createElement("input",{type:"password",onChange:this.watcher.bind(this),className:"form-control",id:"inputSenha",placeholder:"Senha"}))),s.a.createElement("div",{className:"row"},s.a.createElement("button",{onClick:this.submeter.bind(this),className:"btn btn-primary offset-6 col-5"},"Logar")),s.a.createElement("div",{className:"row",id:"mensagemErro",style:{display:"none",marginTop:"5px"}},s.a.createElement("div",{className:"alert alert-danger col-10 offset-1",role:"alert"},"Falha no login")))}}]),t}(o.Component),v=window.$,f=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={nome:"Carregando...",urlImg:""},a.getDados(),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"logout",value:function(){v.ajax({url:"https://podcre-223420.appspot.com/api/Login",type:"DELETE",success:this.props.callbackLogout,error:function(){}})}},{key:"getDados",value:function(){v.ajax({url:"https://podcre-223420.appspot.com/api/user?nome="+this.props.nome,type:"GET",headers:{"token-id":"frontend_podcre"},success:this.preencher.bind(this),error:function(){}})}},{key:"preencher",value:function(e){e=e.data,this.setState({nome:this.props.nome}),this.setState({urlImg:"https://podcre-223420.appspot.com/api/getFile?cod="+e.imagem_blob})}},{key:"render",value:function(){return s.a.createElement("div",{style:{marginTop:"10px"}},s.a.createElement("img",{src:this.state.urlImg,style:{width:"50%",cursor:"pointer"},className:"img-thumbnail",alt:"Imagem de perfil","data-toggle":"modal","data-target":"#setarImagem"}),s.a.createElement("h4",null,this.state.nome),s.a.createElement("div",{className:"row"},s.a.createElement("button",{onClick:this.logout.bind(this),className:"btn btn-outline-danger offset-6 col-5"},"Logout")))}}]),t}(o.Component),g=window.$,k=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={logado:!1,nome:""},a.setLogado=a.setLogado.bind(Object(u.a)(Object(u.a)(a))),a.checarLogado=a.checarLogado.bind(Object(u.a)(Object(u.a)(a))),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){this.checarLogado()}},{key:"loginFeito",value:function(e){this.setState({nome:e}),this.setLogado(!0)}},{key:"checarLogado",value:function(){var e=this;g.ajax({url:"https://podcre-223420.appspot.com/api/Login",type:"GET",success:function(t){e.setState({nome:t.nome}),e.setLogado(!0)},error:function(t){e.setLogado(!1)}})}},{key:"setLogado",value:function(e){e?(this.setState({logado:!0}),void 0!==this.props.callbackLogado&&null!==this.props.callbackLogado&&this.props.callbackLogado(this.state.nome)):(this.setState({logado:!1}),void 0!==this.props.callbackDeslogado&&null!==this.props.callbackDeslogado&&this.props.callbackDeslogado())}},{key:"render",value:function(){var e=this,t=s.a.createElement(b,{callbackSucesso:this.loginFeito.bind(this)});return this.state.logado&&(t=s.a.createElement(f,{nome:this.state.nome,callbackLogout:function(){e.setLogado(!1)}})),s.a.createElement("div",{id:"barraLogin",className:"col-md-2 d-none d-md-block"},t)}}]),t}(o.Component),E=function(e){function t(){return Object(r.a)(this,t),Object(c.a)(this,Object(d.a)(t).apply(this,arguments))}return Object(m.a)(t,e),Object(l.a)(t,[{key:"render",value:function(){return s.a.createElement("div",{id:"infosUser"},s.a.createElement("h1",null,this.props.nomeUser),s.a.createElement("h6",null,this.props.email))}}]),t}(o.Component),y=window.$,j=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={url:"/"},a.buscarURLUp=a.buscarURLUp.bind(Object(u.a)(Object(u.a)(a))),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){this.buscarURLUp()}},{key:"buscarURLUp",value:function(){var e=this;y.ajax({url:"https://podcre-223420.appspot.com/api/upPodcast",type:"GET",success:function(t){e.setState({url:t.url})},error:function(){setTimeout(e.buscarURLUp,1e3)}})}},{key:"toggle",value:function(){"none"===y("#formUpPodcast").css("display")?y("#formUpPodcast").css("display","block"):y("#formUpPodcast").css("display","none")}},{key:"setNome",value:function(){var e=y("#nomePodcast").val();this.setCookie("nome",e)}},{key:"setAssunto",value:function(){var e=y("#assuntoPodcast").val();this.setCookie("assunto",e)}},{key:"render",value:function(){return s.a.createElement("div",{id:"upPodcast"},s.a.createElement("div",{className:"row"},s.a.createElement("div",{className:"col-9"},s.a.createElement("h3",null,"Fa\xe7a upload de um epis\xf3dio:")),s.a.createElement("div",{className:"col-3"},s.a.createElement("button",{onClick:this.toggle,type:"button",className:"btn btn-outline-info"},"Upload"))),s.a.createElement("form",{id:"formUpPodcast",style:{display:"none"},action:this.state.url,method:"post",encType:"multipart/form-data"},s.a.createElement("div",{className:"form-group"},s.a.createElement("label",{htmlFor:"nomePodcast"},"Nome do epis\xf3dio"),s.a.createElement("input",{type:"text",onChange:this.setNome.bind(this),className:"form-control",id:"nomePodcast",placeholder:"Nome"})),s.a.createElement("div",{className:"form-group"},s.a.createElement("label",{htmlFor:"assuntoPodcast"},"Assunto do epis\xf3dio"),s.a.createElement("input",{type:"text",onChange:this.setAssunto.bind(this),className:"form-control",id:"assuntoPodcast",placeholder:"Assunto"})),s.a.createElement("div",{className:"custom-file"},s.a.createElement("input",{type:"file",className:"custom-file-input",id:"arquivoPodcast",name:"arquivo"}),s.a.createElement("label",{className:"custom-file-label",htmlFor:"arquivoPodcast"},"Escolha o arquivo")),s.a.createElement("div",{style:{width:"100%",marginTop:"10px"}},s.a.createElement("input",{type:"submit",className:"btn btn-success ml-auto d-block",value:"enviar"}))))}},{key:"setCookie",value:function(e,t){var a=new Date;a.setTime(a.getTime()+18e5);var o="expires="+a.toGMTString();document.cookie=e+"="+t+"; "+o}},{key:"getCookie",value:function(e){for(var t=e+"=",a=document.cookie.split(";"),o=0;o<a.length;o++){for(var s=a[o];" "===s.charAt(0);)s=s.substring(1);if(0===s.indexOf(t))return s.substring(t.length,s.length)}return""}}]),t}(o.Component),N=a(17),O=a.n(N),L=window.$,w=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={likeDado:!1,dislikeDado:!1,nLikes:0,nDislikes:0},a.likeDado=a.likeDado.bind(Object(u.a)(Object(u.a)(a))),a.dislikeDado=a.dislikeDado.bind(Object(u.a)(Object(u.a)(a))),a.enviarGeoloc=a.enviarGeoloc.bind(Object(u.a)(Object(u.a)(a))),a.audioTocado=a.audioTocado.bind(Object(u.a)(Object(u.a)(a))),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){this.setState({nLikes:this.props.likes}),this.setState({nDislikes:this.props.dislikes})}},{key:"audioTocado",value:function(){navigator.geolocation&&navigator.geolocation.getCurrentPosition(this.enviarGeoloc)}},{key:"enviarGeoloc",value:function(e){L.ajax({url:"https://cors-anywhere.herokuapp.com/108.61.23.245/v1/contextEntities",headers:{"Content-Type":"application/json","X-Requested-With":"developinstance"},data:JSON.stringify({type:"GeolocsOuvintes",isPattern:"false",id:this.props.nomeUser,attributes:[{name:"latitude",type:"float",value:e.coords.latitude.toString()},{name:"longitude",type:"float",value:e.coords.longitude.toString()}]}),type:"POST",success:function(){},error:function(){}})}},{key:"likeDado",value:function(){this.state.likeDado||this.state.dislikeDado||(L("#like_"+this.props.chave).removeClass("fa-thumbs-o-up"),L("#like_"+this.props.chave).addClass("fa-thumbs-up"),this.setState({likeDado:!0}),this.setState({nLikes:Number(this.state.nLikes)+1}),this.submeterLike())}},{key:"dislikeDado",value:function(){this.state.dislikeDado||this.state.likeDado||(L("#dislike_"+this.props.chave).removeClass("fa-thumbs-o-down"),L("#dislike_"+this.props.chave).addClass("fa-thumbs-down"),this.setState({dislikeDado:!0}),this.setState({nDislikes:Number(this.state.nDislikes)+1}),this.submeterDislike())}},{key:"render",value:function(){return s.a.createElement("div",{className:"col-12 painelEscutar"},s.a.createElement("h1",null,this.props.nome),s.a.createElement("p",{className:"text-muted"},this.props.assunto),s.a.createElement(O.a,{src:"https://podcre-223420.appspot.com/api/getFile?cod="+this.props.blob,autoPlay:!1,controls:!0,onPlay:this.audioTocado.bind(this)}),s.a.createElement("div",null,s.a.createElement("span",{id:"like_"+this.props.chave,onClick:this.likeDado.bind(this),className:"fa fa-thumbs-o-up","aria-hidden":"true"}," ",this.state.nLikes),s.a.createElement("span",{id:"dislike_"+this.props.chave,onClick:this.dislikeDado.bind(this),className:"fa fa-thumbs-o-down","aria-hidden":"true"}," ",this.state.nDislikes)))}},{key:"submeterLike",value:function(){L.ajax({url:"https://podcre-223420.appspot.com/api/contar?qual=like&cod="+this.props.chave,type:"GET",headers:{"token-id":"frontend_podcre"},success:function(){},error:function(){}})}},{key:"submeterDislike",value:function(){L.ajax({url:"https://podcre-223420.appspot.com/api/contar?qual=dislike&cod="+this.props.chave,type:"GET",headers:{"token-id":"frontend_podcre"},success:function(){},error:function(){}})}}]),t}(o.Component),D=a(18),U=a.n(D),C=window.$,P=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={listaPontos:[]},a.requisitarPontos=a.requisitarPontos.bind(Object(u.a)(Object(u.a)(a))),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){var e=this;setInterval(function(){e.requisitarPontos()},5e3)}},{key:"requisitarPontos",value:function(){var e=this;C.ajax({url:"https://podcre-223420.appspot.com/api/Geoloc?nome="+this.props.nome,type:"GET",headers:{"token-id":"frontend_podcre"},success:function(t){e.setState({listaPontos:t.data.map(function(e){var t=e.split(", ");return{lat:Number(t[0]),lng:Number(t[1])}})})},error:function(){e.setState({listaPontos:[]})}})}},{key:"render",value:function(){for(var e=[],t=0;t<this.state.listaPontos.length;t++)e.push(s.a.createElement(S,Object.assign({key:t},this.state.listaPontos[t])));return s.a.createElement("div",{style:{height:"100vh"}},s.a.createElement(U.a,{bootstrapURLKeys:{key:"AIzaSyDu0SHdChKTWlE0do-KImsvfBpwIHLp_u0"},defaultCenter:{lat:0,lng:0},defaultZoom:1},e))}}]),t}(o.Component),S=function(){return s.a.createElement("div",{style:{position:"absolute",width:10,height:10,left:-5,top:-5,borderRadius:10,backgroundColor:"#0074D9"}})},T=window.$,x=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={nome:"",nomeUser:"",email:"",listaPodcasts:[]},a.buscarDados=a.buscarDados.bind(Object(u.a)(Object(u.a)(a))),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){this.buscarDados(),this.buscarDadosPodcasts()}},{key:"buscarDados",value:function(){var e=this;T.ajax({url:"https://podcre-223420.appspot.com/api/user?nome="+this.props.nomeUser,type:"GET",headers:{"token-id":"frontend_podcre"},success:function(t){e.setState({nome:t.data.nome_display,nomeUser:t.data.nome_user,email:t.data.email})},error:function(){setTimeout(e.buscarDados,1e3)}})}},{key:"buscarDadosPodcasts",value:function(){var e=this;T.ajax({url:"https://podcre-223420.appspot.com/api/getPodcasts?nome="+this.props.nomeUser,type:"GET",headers:{"token-id":"frontend_podcre"},success:function(t){e.setState({listaPodcasts:t.data})},error:function(){setTimeout(e.buscarDados,1e3)}})}},{key:"toggle",value:function(){"none"===T("#painelPodcastsLogado").css("display")?T("#painelPodcastsLogado").css("display","block"):T("#painelPodcastsLogado").css("display","none")}},{key:"render",value:function(){var e=this,t=this.state.listaPodcasts.map(function(t){return s.a.createElement(w,{nomeUser:e.state.nomeUser,nome:t.nome,assunto:t.assunto,likes:t.n_likes,dislikes:t.n_dislikes,blob:t.key_blob,chave:t.chave,key:t.chave})});return s.a.createElement("div",{id:"painelLogado",className:"col-md-8"},s.a.createElement(E,{nomeUser:this.state.nome,email:this.state.email}),s.a.createElement(j,{nomeUser:this.props.nomeUser,email:this.state.email}),s.a.createElement("div",{className:"row",style:{marginTop:"10px"}},s.a.createElement("div",{className:"col-9"},s.a.createElement("h3",null,"Seus podcasts:")),s.a.createElement("div",{className:"col-3"},s.a.createElement("button",{onClick:this.toggle,type:"button",className:"btn btn-outline-info"},"Mostrar"))),s.a.createElement("div",{className:"row",id:"painelPodcastsLogado",style:{display:"none"}},t),s.a.createElement("div",{className:"row",style:{marginTop:"10px"}},s.a.createElement("div",{className:"col-12"},s.a.createElement("h3",null,"Mapa de ouvintes:"))),s.a.createElement("div",{className:"row",style:{marginTop:"20px"}},s.a.createElement("div",{className:"col-12"},s.a.createElement(P,{nome:this.props.nomeUser}))))}}]),t}(o.Component),_=window.$,F=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={lista:[]},a.buscarDados=a.buscarDados.bind(Object(u.a)(Object(u.a)(a))),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){for(var e=0;e<this.props.listaNomes.length;e++)this.buscarDados(this.props.listaNomes[e])}},{key:"componentWillReceiveProps",value:function(e){for(var t=0;t<this.props.listaNomes.length;t++)this.buscarDados(this.props.listaNomes[t])}},{key:"buscarDados",value:function(e){var t=this;_.ajax({url:"https://podcre-223420.appspot.com/api/getPodcasts?nome="+e,type:"GET",headers:{"token-id":"frontend_podcre"},success:function(a){var o={nome:e,lista:a.data},s=t.state.lista.slice();s.push(o),t.setState({lista:s})},error:function(){setTimeout(t.buscarDados,1e3)}})}},{key:"render",value:function(){for(var e=this,t=[],a=function(a){var o=e.state.lista[a].lista.map(function(t){return s.a.createElement(w,{nomeUser:e.state.lista[a].nome,nome:t.nome,assunto:t.assunto,likes:t.n_likes,dislikes:t.n_dislikes,blob:t.key_blob,chave:t.chave,key:t.chave})});o.length>0&&t.push(s.a.createElement("div",{className:"row",key:a},s.a.createElement("div",{className:"col-12",style:{textAlign:"right",marginTop:"20px"}},s.a.createElement("h4",null,e.state.lista[a].nome)),o))},o=0;o<this.state.lista.length;o++)a(o);return s.a.createElement("div",{id:"painelDeslogado",className:"col-md-8"},s.a.createElement("h1",null,"Principais podcasts"),s.a.createElement("hr",null),t)}}]),t}(o.Component),G=window.$,q=function(e){function t(){return Object(r.a)(this,t),Object(c.a)(this,Object(d.a)(t).apply(this,arguments))}return Object(m.a)(t,e),Object(l.a)(t,[{key:"registrar",value:function(){var e={nome:G("#nomeUser").val(),display:G("#nomeDisplay").val(),email:G("#emailUser").val(),senha:G("#senhaUser").val()};G.ajax({url:"https://podcre-223420.appspot.com/api/user",type:"POST",data:JSON.stringify(e),success:function(e){window.location.reload()},error:function(){G("#alertaErroCadastro").css("display","block"),setTimeout(function(){G("#alertaErroCadastro").css("display","none")},2e3)}})}},{key:"render",value:function(){return s.a.createElement("div",{className:"modal fade",id:"cadastrarModal",tabIndex:"-1",role:"dialog","aria-labelledby":"modalLabel","aria-hidden":"true"},s.a.createElement("div",{className:"modal-dialog",role:"document"},s.a.createElement("div",{className:"modal-content"},s.a.createElement("div",{className:"modal-header"},s.a.createElement("h5",{className:"modal-title",id:"modalLabel"},"Cadastrar-se"),s.a.createElement("button",{type:"button",className:"close","data-dismiss":"modal","aria-label":"Close"},s.a.createElement("span",{"aria-hidden":"true"},"\xd7"))),s.a.createElement("div",{className:"modal-body"},s.a.createElement("form",{id:"formUpPodcast"},s.a.createElement("div",{className:"form-group"},s.a.createElement("label",{htmlFor:"nomeUser"},"Nome de usu\xe1rio"),s.a.createElement("input",{type:"text",className:"form-control",id:"nomeUser",placeholder:"Nome"})),s.a.createElement("div",{className:"form-group"},s.a.createElement("label",{htmlFor:"nomeDisplay"},"Nome de display"),s.a.createElement("input",{type:"text",className:"form-control",id:"nomeDisplay",placeholder:"Display"})),s.a.createElement("div",{className:"form-group"},s.a.createElement("label",{htmlFor:"senhaUser"},"Senha"),s.a.createElement("input",{type:"password",className:"form-control",id:"senhaUser",placeholder:"Escolha uma senha"})),s.a.createElement("div",{className:"form-group"},s.a.createElement("label",{htmlFor:"emailUser"},"E-Mail"),s.a.createElement("input",{type:"text",className:"form-control",id:"emailUser",placeholder:"email"})))),s.a.createElement("div",{className:"modal-footer"},s.a.createElement("div",{className:"alert alert-danger",style:{display:"none"},id:"alertaErroCadastro",role:"alert"},"Falha no cadastro"),s.a.createElement("button",{type:"button",onClick:this.registrar.bind(this),className:"btn btn-primary"},"Cadastrar")))))}}]),t}(o.Component),I=window.$,M=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={url:"/"},a.buscarURLUp=a.buscarURLUp.bind(Object(u.a)(Object(u.a)(a))),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){this.buscarURLUp()}},{key:"buscarURLUp",value:function(){var e=this;I.ajax({url:"https://podcre-223420.appspot.com/api/setImagemPerfil",type:"GET",success:function(t){e.setState({url:t.url})},error:function(){setTimeout(e.buscarURLUp,1e3)}})}},{key:"render",value:function(){return s.a.createElement("div",{className:"modal fade",id:"setarImagem",tabIndex:"-1",role:"dialog","aria-labelledby":"modalLabel","aria-hidden":"true"},s.a.createElement("div",{className:"modal-dialog",role:"document"},s.a.createElement("div",{className:"modal-content"},s.a.createElement("div",{className:"modal-header"},s.a.createElement("h5",{className:"modal-title",id:"modalLabel"},"Atualizar imagem"),s.a.createElement("button",{type:"button",className:"close","data-dismiss":"modal","aria-label":"Close"},s.a.createElement("span",{"aria-hidden":"true"},"\xd7"))),s.a.createElement("div",{className:"modal-body"},s.a.createElement("form",{id:"formUpImagem",action:this.state.url,method:"post",encType:"multipart/form-data"},s.a.createElement("div",{className:"custom-file"},s.a.createElement("input",{type:"file",className:"custom-file-input",id:"arquivoPodcast",name:"arquivo"}),s.a.createElement("label",{className:"custom-file-label",htmlFor:"arquivoPodcast"},"Escolha o arquivo")),s.a.createElement("div",{className:"modal-footer"},s.a.createElement("div",{className:"alert alert-danger",style:{display:"none"},id:"alertaErroCadastro",role:"alert"},"Falha no envio"),s.a.createElement("button",{type:"submit",className:"btn btn-primary"},"Upload")))))))}}]),t}(o.Component),R=window.$,$=function(e){function t(e){var a;return Object(r.a)(this,t),(a=Object(c.a)(this,Object(d.a)(t).call(this,e))).state={estaLogado:!1,nomeUser:"",listaNomes:[]},a.logado=a.logado.bind(Object(u.a)(Object(u.a)(a))),a.deslogado=a.deslogado.bind(Object(u.a)(Object(u.a)(a))),a.preencherLista=a.preencherLista.bind(Object(u.a)(Object(u.a)(a))),a}return Object(m.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){this.preencherLista()}},{key:"preencherLista",value:function(){var e=this;R.ajax({url:"https://podcre-223420.appspot.com/api/Estatisticas/listaNomes",type:"GET",headers:{"token-id":"frontend_podcre"},success:function(t){var a=t.data;e.setState({listaNomes:a}),e.forceUpdate()},error:function(){}})}},{key:"logado",value:function(e){this.setState({nomeUser:e}),this.setState({estaLogado:!0})}},{key:"deslogado",value:function(){this.setState({estaLogado:!1})}},{key:"render",value:function(){var e=null;return e=this.state.estaLogado?s.a.createElement(x,{nomeUser:this.state.nomeUser}):s.a.createElement(F,{listaNomes:this.state.listaNomes}),s.a.createElement("div",null,s.a.createElement(p,{logado:this.state.estaLogado}),s.a.createElement("div",{className:"row",id:"corpo"},s.a.createElement(k,{callbackLogado:this.logado,callbackDeslogado:this.deslogado}),e),s.a.createElement(q,{CallbackLogado:this.logado}),s.a.createElement(M,null))}}]),t}(o.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));i.a.render(s.a.createElement($,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})}},[[19,2,1]]]);
//# sourceMappingURL=main.9d608c8a.chunk.js.map
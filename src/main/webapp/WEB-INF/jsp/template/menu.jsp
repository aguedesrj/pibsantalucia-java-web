<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<s:hidden id="MSG_SAIR_SISTEMA" value="%{getText('MSG.SAIR.SISTEMA')}"/>

<script src="../resources/js/pages/menu.js"></script>

<div class="container" style="width: 860px;">
	<div class="row">
		<div class="navbar navbar-default" role="navigation">
	        <div class="collapse navbar-collapse">
	            <ul class="nav navbar-nav">
	                <li class="active"><a href="../Usuario/Home.action">Home</a></li>
	                <li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Congregado <b class="caret"></b></a>
	                    <ul class="dropdown-menu">
	                        <li><a href="../Congregado/CadastroCongregado.action">Novo</a></li>
	                        <li><a href="../Congregado/PesquisaCongregado.action">Pesquisar</a></li>
	                    </ul>	                
	                </li>
	                <li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Pastoral <b class="caret"></b></a>
	                    <ul class="dropdown-menu">
	                        <li><a href="../Pastoral/CadastroPastoral.action">Novo</a></li>
	                        <li><a href="../Pastoral/ListarPastoral.action">Pesquisar</a></li>
	                    </ul>	                
	                </li>	                
	                <li><a href="javascript:actionSair();">Sair</a></li>
	            </ul>
	        </div>
	    </div>
	</div>
</div>

<%-- <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container" style="width: 860px;">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastro <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action [Menu 1.1]</a></li>
                        <li><a href="#">Another action [Menu 1.1]</a></li>
                        <li><a href="#">Something else here [Menu 1.1]</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link [Menu 1.1]</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link [Menu 1.1]</a></li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown [Menu 1.1] <b class="caret"></b></a>

                            <ul class="dropdown-menu">
                                <li><a href="#">Action [Menu 1.2]</a></li>
                                <li>
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown [Menu 1.2] <b class="caret"></b></a>

                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown [Menu 1.3] <b class="caret"></b></a>

                                            <ul class="dropdown-menu">
                                                <li><a href="#">Action [Menu 1.4]</a></li>
                                                <li><a href="#">Another action [Menu 1.4]</a></li>
                                                <li><a href="#">Something else here [Menu 1.4]</a></li>
                                                <li class="divider"></li>
                                                <li><a href="#">Separated link [Menu 1.4]</a></li>
                                                <li class="divider"></li>
                                                <li><a href="#">One more separated link [Menu 1.4]</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu 2 <b class="caret"></b></a>

                    <ul class="dropdown-menu">
                        <li><a href="#">Action [Menu 2.1]</a></li>
                        <li><a href="#">Another action [Menu 2.1]</a></li>
                        <li><a href="#">Something else here [Menu 2.1]</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link [Menu 2.1]</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link [Menu 2.1]</a></li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown [Menu 2.1] <b class="caret"></b></a>

                            <ul class="dropdown-menu">
                                <li><a href="#">Action [Menu 2.2]</a></li>
                                <li><a href="#">Another action [Menu 2.2]</a></li>
                                <li><a href="#">Something else here [Menu 2.2]</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Separated link [Menu 2.2]</a></li>
                                <li class="divider"></li>
                                <li>
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown [Menu 2.2] <b class="caret"></b></a>

                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown [Menu 2.3] <b class="caret"></b></a>

                                            <ul class="dropdown-menu">
                                                <li><a href="#">Action [Menu 2.4]</a></li>
                                                <li><a href="#">Another action [Menu 2.4]</a></li>
                                                <li><a href="#">Something else here [Menu 2.4]</a></li>
                                                <li class="divider"></li>
                                                <li><a href="#">Separated link [Menu 2.4]</a></li>
                                                <li class="divider"></li>
                                                <li><a href="#">One more separated link [Menu 2.4]</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div> --%>

<!-- <div class="container" style="width: 860px;">
	<div class="row">
		<nav class="navbar navbar-default" role="navigation">
   			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      			<ul class="nav navbar-nav">
        			<li><a href="#">Home</a></li>
        			<li class="dropdown">
          				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastro <b class="caret"></b></a>
	          			<ul class="dropdown-menu">
	          				<li><a href="../Membro/Cadastro">Membros</a></li>
	          				<li><a href="#">Usu￡rios</a></li>
	          			</ul>
        			</li>
      			</ul>
      			<ul class="nav navbar-nav navbar-right">
        			<li><a href="#" onclick="javascript:actionSair();">Sair</a></li>
      			</ul>
    		</div>
		</nav>
	</div>
</div> -->
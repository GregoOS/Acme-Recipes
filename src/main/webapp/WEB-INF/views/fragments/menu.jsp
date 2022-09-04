<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Chef,acme.roles.Epicure"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.greortsol" action="https://es.nttdata.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.ferclavar" action="https://www.youtube.com/shorts/GLY3-81TxDA"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.pabsanval1" action="https://www.youtube.com/watch?v=dQw4w9WgXcQ"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.mancabmor1" action="https://www.youtube.com/watch?v=-3JbbFL-aks"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.alegestor" action="https://twitter.com/juanminismo/status/1464982823874486274"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.jaistomen" action="http://www.gendesign.co.jp/E_index.html"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any">			
			<acme:menu-suboption code="master.menu.any.peep.list-recent" action="/any/peep/list-recent"/>
			<acme:menu-suboption code="master.menu.any.element.list" action="/any/element/list"/>
			<acme:menu-suboption code="master.menu.any.useraccount.list" action="/any/user-account/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">			
			<acme:menu-suboption code="master.menu.authenticated.bulletin.list-recent" action="/authenticated/bulletin/list-recent"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.chef" access="hasRole('Chef')">
			<acme:menu-suboption code="master.menu.chef.element.list" action="/chef/element/list"/>
			<acme:menu-suboption code="master.menu.chef.finedish.list" action="/chef/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.chef.finedish.listproposed" action="/chef/fine-dish/list-proposed"/>
			<acme:menu-suboption code="master.menu.chef.memorandum.list" action="/chef/memorandum/list"/>
			<acme:menu-suboption code="master.menu.chef.recipe.list" action="/chef/recipe/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.epicure" access="hasRole('Epicure')">
			<acme:menu-suboption code="master.menu.epicure.dashboard" action="/epicure/epicure-dashboard/show"/>
			<acme:menu-suboption code="master.menu.epicure.finedish.list" action="/epicure/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.epicure.memorandum.list" action="/epicure/memorandum/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.bulletin.create" action="/administrator/bulletin/create"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.admin-dashboard" action="/administrator/admin-dashboard/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration.show" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-chef" action="/authenticated/chef/create" access="!hasRole('Chef')"/>
			<acme:menu-suboption code="master.menu.user-account.chef" action="/authenticated/chef/update" access="hasRole('Chef')"/>
			<acme:menu-suboption code="master.menu.user-account.become-epicure" action="/authenticated/epicure/create" access="!hasRole('Epicure')"/>
			<acme:menu-suboption code="master.menu.user-account.epicure" action="/authenticated/epicure/update" access="hasRole('Epicure')"/>

		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>


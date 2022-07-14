package acme.features.epicure.finedish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.finedish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureFineDishController extends AbstractController<Epicure, FineDish> {

	
	// Internal state ---------------------------------------------------------
	
		@Autowired
		protected EpicureFineDishListService list;
		
		@Autowired
		protected EpicureFineDishShowService show;
		
		@Autowired
		protected EpicureFineDishCreateService create;
		
		@Autowired
		protected EpicureFineDishUpdateService update;
		
		@Autowired
		protected EpicureFineDishPublishService publish;
		
		@Autowired
		protected EpicureFineDishDeleteService delete;
		
		
		// Constructors -----------------------------------------------------------
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.list);
			super.addCommand("show", this.show);
			super.addCommand("create", this.create);
			super.addCommand("update", this.update);
			super.addCommand("publish", "update", this.publish);
			super.addCommand("delete", this.delete);
			
		}
}
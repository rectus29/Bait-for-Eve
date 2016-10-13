package com.rectuscorp.evetool.web.page.prodplan;
/*-----------------------------------------------------*/
/*       _____           _               ___   ___     */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 28/04/13 17:48                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectuscorp.evetool.entities.crest.MarketGroup;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class ProdPlanPage extends ProtectedPage {

	@SpringBean
	IserviceUser serviceUser;

	private MarketGroup marketGroup, marketGroupLv1, marketGroupLv2;
	private Form form;
	private FormComponent lvChild, lvFinal, lvMarketGroup;

	public ProdPlanPage() {
	}

	public ProdPlanPage(IModel<?> model) {
		super(model);
	}

	public ProdPlanPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add((form = new Form("form")).setOutputMarkupId(true));

		/*form.add(new DropDownChoice<MarketGroup>(
				"lv",
				new PropertyModel<MarketGroup>(this, "marketGroup"),
				new ArrayList<MarketGroup>(serviceInvmarketgroups.getAllByProperty("parentGroupID",
						serviceInvmarketgroups.getByProperty("marketGroupId", 2l, true))),
				new ChoiceRenderer<MarketGroup>("marketGroupName")
		).add(new AjaxFormComponentUpdatingBehavior("onChange") {
			@Override
			protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
				ajaxRequestTarget.add(lvChild);
			}
		}));

		form.add((lvChild = new DropDownChoice<MarketGroup>(
				"lvChild",
				new PropertyModel<MarketGroup>(this, "marketGroupLv1"),
				new LoadableDetachableModel<List<? extends MarketGroup>>() {
					@Override
					protected List<? extends MarketGroup> load() {
						return new ArrayList<MarketGroup>(serviceInvmarketgroups.getAllByProperty("parentGroupID", marketGroup));
					}
				},
				new ChoiceRenderer<MarketGroup>("marketGroupName")
		)).add(new AjaxFormComponentUpdatingBehavior("onChange") {
				   @Override
				   protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
					   ajaxRequestTarget.add(lvMarketGroup, lvFinal);
				   }
			   }
		).setOutputMarkupId(true));

		form.add((lvMarketGroup = new DropDownChoice<MarketGroup>(
				"marketGroupLv2",
				new PropertyModel<MarketGroup>(this, "marketGroupLv2"),
				new LoadableDetachableModel<List<? extends MarketGroup>>() {
					@Override
					protected List<? extends MarketGroup> load() {
						return new ArrayList<MarketGroup>(serviceInvmarketgroups.getAllByProperty("parentGroupID", marketGroupLv1));
					}
				},
				new ChoiceRenderer<MarketGroup>("marketGroupName")
		)).add(new AjaxFormComponentUpdatingBehavior("onChange") {
				   @Override
				   protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
					   ajaxRequestTarget.add(lvFinal);
				   }
			   }
		).setOutputMarkupId(true));
*/
	}
}

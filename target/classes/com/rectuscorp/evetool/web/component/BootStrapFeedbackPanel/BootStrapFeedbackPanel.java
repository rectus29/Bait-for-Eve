package com.rectuscorp.evetool.web.component.BootStrapFeedbackPanel;


/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus for          Date: 04/12/12 14:52 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class BootStrapFeedbackPanel extends FeedbackPanel{

    public BootStrapFeedbackPanel(String id) {
        super(id);
    }

    public BootStrapFeedbackPanel(String id, IFeedbackMessageFilter filter) {
        super(id, filter);
    }



    @Override
    protected String getCSSClass(FeedbackMessage message) {
        switch (message.getLevel()){
            case 100:
                return "alert alert-info";
            case 200:
                return "alert alert-success";

            case 250:
                return "alert alert-success";
            case 300:
                return "alert";
            case 400:
                return "alert alert-error";
            default:
                return "alert";
        }
    }
}

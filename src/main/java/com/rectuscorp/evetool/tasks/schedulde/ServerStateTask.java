package com.rectuscorp.evetool.tasks.schedulde;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 30/06/2016 15:20               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

import com.rectuscorp.evetool.api.EveXmlApi;
import com.rectuscorp.evetool.entities.core.Config;
import com.rectuscorp.evetool.service.impl.ServiceGeneric;
import com.rectuscorp.evetool.tasks.Task;

/**
 * this task check server state every 3 minute
 * and set config with it
 */
public class ServerStateTask extends Task {

	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(ServerStateTask.class);
	private static String serverOpen = "serverOpen";
	private static String onlinePlayers = "onlinePlayers";

	@Autowired
	@Qualifier("serviceGeneric")
	private ServiceGeneric serviceGeneric;

	@Override
	@Scheduled(cron = "0/3 * * * * *")
	public void process() {
		log.debug("start task");
		Map<String, Object> out = EveXmlApi.get().getServerState();
		if (out.get(serverOpen) != null) {
			serviceGeneric.save(new Config(serverOpen, (String) out.get(serverOpen)));
		}
		if (out.get(onlinePlayers) != null) {
			serviceGeneric.save(new Config(onlinePlayers, (String) out.get(onlinePlayers)));
		}
		log.debug("task done");
	}
}

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

import com.rectuscorp.evetool.api.EveXmlApi;
import com.rectuscorp.evetool.entities.core.Config;
import com.rectuscorp.evetool.service.IserviceConfig;
import com.rectuscorp.evetool.tasks.Task;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * this task check server state every 3 minute
 * and set config with it
 */
@Transactional
@Service
public class ServerStateTask extends Task {

	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(ServerStateTask.class);
	public static final String SERVEROPEN = "SERVEROPEN";
	public static final String ONLINEPLAYERS = "ONLINEPLAYERS";
	@Autowired
	@Qualifier("serviceConfig")
	private IserviceConfig serviceConfig;

	@Override
	@Scheduled(cron = "0 0/1 * * * *")
	public void process() {
		log.debug("start task");
		Map<String, Object> out = EveXmlApi.get().getServerState();
		if (out.get(SERVEROPEN) != null) {
			serviceConfig.save(new Config(SERVEROPEN, (String) out.get(SERVEROPEN)));
		}
		if (out.get(ONLINEPLAYERS) != null) {
			serviceConfig.save(new Config(ONLINEPLAYERS, (String) out.get(ONLINEPLAYERS)));
		}
		log.debug("task done");
	}
}

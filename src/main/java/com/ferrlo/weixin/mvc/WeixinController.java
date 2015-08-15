package com.ferrlo.weixin.mvc;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ferrlo.weixin.domain.Message;
import com.ferrlo.weixin.domain.Reply;
import com.ferrlo.weixin.service.MessageService;
import com.ferrlo.weixin.util.WeixinUtil;

@Controller
@RequestMapping("/weixin")
public class WeixinController {

	private static final String TOKEN = "hskjwechat";
	@Autowired
	@Qualifier("msgService")
	private MessageService msgService;

	// 微信公众平台验证url是否有效使用的接口
	@RequestMapping(method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String initWechatURL(HttpServletRequest request) {
		String echostr = request.getParameter("echostr");
		if (checkWechatReques(request) && echostr != null) {
			return echostr;
		} else {
			return "error";
		}
	}

	/**
	 * 根据token计算signature验证是否为weixin服务端发送的消息
	 */
	private static boolean checkWechatReques(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (signature != null && timestamp != null && nonce != null) {
			String[] strSet = new String[] { TOKEN, timestamp, nonce };
			java.util.Arrays.sort(strSet);
			String key = "";
			for (String string : strSet) {
				key = key + string;
			}
			String pwd = WeixinUtil.sha1(key);
			return pwd.equals(signature);
		} else {
			return false;
		}
	}

	// 接收微信公众号接收的消息，处理后再做相应的回复
	@RequestMapping(method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String replyMessage(HttpServletRequest request) {
		if (checkWechatReques(request)) {
			Map<String, String> requestMap = WeixinUtil.parseXml(request);
			Message message = WeixinUtil.mapToMessage(requestMap);
			// 存储信息
			String replyContent = msgService.replyMessage(message);
			Reply reply = new Reply();
			reply.setToUserName(message.getFromUserName());
			reply.setFromUserName(message.getToUserName());
			reply.setCreateTime(new Date());
			reply.setMsgType(Reply.TEXT);
			reply.setContent(replyContent);
			// 存储回复消息
			String back = WeixinUtil.replyToXml(reply);
			return back;

		} else {
			return "error";
		}
	}

	public MessageService getMsgService() {
		return msgService;
	}

	public void setMsgService(MessageService msgService) {
		this.msgService = msgService;
	}

}

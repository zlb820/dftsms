package cn.zlb.tools.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;


@ServerEndpoint("/wserver")
public class WebsocketServer {
	//定义 session session 是 websocket的session
	@SuppressWarnings("unused")
	private Session session;
	
	//定义 map 用于保存 连接到 WebsocketServer 的session
	private static final Map<String , Session> sesMap=new HashMap<String, Session>();
	
	//定义 socket 集合用于存储连接的 所有 WebsocketServer 
	private static Set<WebsocketServer> socketSet=new HashSet<WebsocketServer>();
	
	//定义 busName 获取 商家id号
	private String busId;
	
	Logger logger=Logger.getLogger(WebsocketServer.class);
	
	/**
	   * 打开通道时调用
	   * @param session
	   * @throws IOException 
	   */
	@OnOpen
	public void onOpen(Session session){
		//添加 新的socket到 sockets
		socketSet.add(this);
		
		this.session=session;
		//添加 新的session到 sesMap
		sesMap.put(busId, session);
		
		logger.info("server connect success");
		
	}
	/**	 * 接收到 客户端的消息是 调用
	 * @param msg
	 * @throws IOException
	 */
	@OnMessage
	public void onMessage( String msg) throws IOException{
	 
		sendBus("zlb", msg);
	}
	
	/**
	 * 想指定的 商家发送消息
	 * @param busName
	 * @param msg
	 * @throws IOException 
	 */
	public static void  sendBus(String busId,String msg) throws IOException  {
		
		//获取该用户的session
		Session session=sesMap.get(busId);
		 
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			    sesMap.remove(busId); 
	            try {  
	                session.close();  
	            } catch (IOException e1) {  
	                 
	            } 
		}
		 
	}
	
	/**
	 * 客户端 关闭时 调用
	 * @param session
	 */
	@OnClose
	public void onClose(Session session){
		
		//从 sockets中 移除
		 socketSet.remove(this);
		 
		 logger.info("server  close success");
	}
	
}






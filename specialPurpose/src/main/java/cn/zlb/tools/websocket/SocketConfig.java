package cn.zlb.tools.websocket;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
/**
 * websocket 初始化 webcsocket 连接
 * @author Bingo
 *
 */
public class SocketConfig implements ServerApplicationConfig{
	
	/**
	 * 扫描注解 
	 * 扫描 ServerEndPoint注解 ,初始化的websocketserver
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> endPoint) {
		System.out.println("扫描到的ServerEndPoint数量:"+endPoint.size());
		//必须返回 不返回 无法实现
		return endPoint;
	}
	/**
	 * 扫描配置文件  配置的  websocketserver
	 */
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endPoint) {
		return null;
	}

}

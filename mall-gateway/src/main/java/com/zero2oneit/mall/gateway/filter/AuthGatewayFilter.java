package com.zero2oneit.mall.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.zero2oneit.mall.gateway.config.JwtProperties;
import com.zero2oneit.mall.gateway.utils.JwtUtils;
import com.zero2oneit.mall.gateway.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author jiangli
 * @since 2020/1/28 16:53
 * 过滤器
 */
@Component
public class AuthGatewayFilter implements GlobalFilter, Ordered {

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private JwtUtils jwtUtils;

	/**
	 - 获取cookie中的token
	 - 通过JWT对token进行校验
	 - 通过：则放行；不通过：则响应认证未通过
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 获取request和response，注意：不是HttpServletRequest及HttpServletResponse
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		// 获取headers
		HttpHeaders headers = request.getHeaders();
		// 如果headers为空或者不包含指定的token，则相应认证未通过
		if (CollectionUtils.isEmpty(headers) || !headers.containsKey(this.jwtProperties.getKey())) {
			// 响应未认证！结束请求
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return response.setComplete();
		}
		// 获取token
		String token = headers.getFirst(this.jwtProperties.getKey());
		try {
			// 校验token
			if(!jwtUtils.verifyToken(token)){
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 校验失败，响应未认证
			return result(R.fail("hhhh"), exchange);
		}
		// 认证通过放行
		return chain.filter(exchange);
	}

	/**
	 * 返回值的封装
	 * @param r  返回提示信息
	 */
	private Mono<Void> result(R r, ServerWebExchange exchange) {
		ServerHttpResponse response = exchange.getResponse();
		DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(r).getBytes(StandardCharsets.UTF_8));
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		return response.writeWith(Mono.just(buffer));
	}


	@Override
	public int getOrder() {
		return 0;
	}

}

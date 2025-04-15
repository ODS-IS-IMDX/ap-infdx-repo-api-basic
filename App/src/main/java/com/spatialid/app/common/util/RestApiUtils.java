// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.util;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * REST API共通クラス<br>
 * 
 * @author NTTData
 * @version 1.00
 */
public class RestApiUtils {

	/**
	 * Locationを設定したHTTPヘッダを作成する.<br>
	 *
	 * @return HTTPヘッダ
	 */
	public static HttpHeaders createHttpHeaders(UriComponentsBuilder ucb, String uriPath, String id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(createURI(ucb, uriPath, id));
		return headers;
	}

	/**
	 * URIを作成する.<br>
	 *
	 * @return URI
	 */
	public static URI createURI(UriComponentsBuilder ucb, String uriPath, String id) {

		return ucb.path(uriPath).buildAndExpand(id).toUri();
	}
}

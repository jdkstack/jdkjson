/**
 * JSON的处理. *
 *
 * <PRE>
 *   参考:
 *     1.雅加达EE JSON-B(内部集成JSON-P)的标准规范:
 *     https://jakarta.ee/specifications/jsonb/2.0/jakarta-jsonb-spec-2.0.html
 *     2.JEP 198 轻量级 JSON API:
 *     http://openjdk.java.net/jeps/198
 * </PRE>
 *
 * <p>JSON的处理包括几个部分.
 *
 * <p>JSON 数据绑定.是在反序列化和序列化的过程中进行映射的操作. *
 *
 * <PRE>
 *    数字映射成整形还是浮点型.
 *    字符串映射成日期类型还是电子邮件类型.
 * </PRE>
 *
 * <p>JSON 反序列化.反序列化是将json字符串转换成json对象之前的过程.
 *
 * <PRE>
 *   之后会进行JSON数据绑定操作.
 * </PRE>
 *
 * <p>JSON 序列化.序列化是将json对象转换成json字符串之前的过程.
 *
 * <PRE>
 *   之后会进行JSON数据绑定操作.
 * </PRE>
 *
 * <p>JSON转换.
 *
 * <PRE>
 *    JSON2PATCH.
 *    PATCH2JSON.
 *    JSON-MERGER)
 * </PRE>
 *
 * <p>JSON管理.
 *
 * <PRE>
 *    JSON-PATH.
 *    JSON-POINTER.
 *    JSON-SCHEME.
 * </PRE>
 *
 * @author admin
 */
package org.jdkstack.jdkjson.core;

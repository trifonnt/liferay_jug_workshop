/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package bg.jug.workshop.liferay.cfp.model.impl;

import aQute.bnd.annotation.ProviderType;

import bg.jug.workshop.liferay.cfp.model.Venue;
import bg.jug.workshop.liferay.cfp.model.VenueModel;
import bg.jug.workshop.liferay.cfp.model.VenueSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Venue service. Represents a row in the &quot;CFP_Venue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link VenueModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VenueImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VenueImpl
 * @see Venue
 * @see VenueModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class VenueModelImpl extends BaseModelImpl<Venue> implements VenueModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a venue model instance should use the {@link Venue} interface instead.
	 */
	public static final String TABLE_NAME = "CFP_Venue";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "venueId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "address", Types.VARCHAR },
			{ "latitude", Types.DOUBLE },
			{ "longitude", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("venueId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("address", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("latitude", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("longitude", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table CFP_Venue (uuid_ VARCHAR(75) null,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,venueId LONG not null primary key,name VARCHAR(75) null,address VARCHAR(1000) null,latitude DOUBLE,longitude DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table CFP_Venue";
	public static final String ORDER_BY_JPQL = " ORDER BY venue.venueId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CFP_Venue.venueId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(bg.jug.workshop.liferay.cfp.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.bg.jug.workshop.liferay.cfp.model.Venue"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(bg.jug.workshop.liferay.cfp.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.bg.jug.workshop.liferay.cfp.model.Venue"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(bg.jug.workshop.liferay.cfp.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.bg.jug.workshop.liferay.cfp.model.Venue"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long VENUEID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Venue toModel(VenueSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Venue model = new VenueImpl();

		model.setUuid(soapModel.getUuid());
		model.setCompanyId(soapModel.getCompanyId());
		model.setGroupId(soapModel.getGroupId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setVenueId(soapModel.getVenueId());
		model.setName(soapModel.getName());
		model.setAddress(soapModel.getAddress());
		model.setLatitude(soapModel.getLatitude());
		model.setLongitude(soapModel.getLongitude());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Venue> toModels(VenueSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Venue> models = new ArrayList<Venue>(soapModels.length);

		for (VenueSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(bg.jug.workshop.liferay.cfp.service.util.ServiceProps.get(
				"lock.expiration.time.bg.jug.workshop.liferay.cfp.model.Venue"));

	public VenueModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _venueId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setVenueId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _venueId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Venue.class;
	}

	@Override
	public String getModelClassName() {
		return Venue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("venueId", getVenueId());
		attributes.put("name", getName());
		attributes.put("address", getAddress());
		attributes.put("latitude", getLatitude());
		attributes.put("longitude", getLongitude());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long venueId = (Long)attributes.get("venueId");

		if (venueId != null) {
			setVenueId(venueId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		Double latitude = (Double)attributes.get("latitude");

		if (latitude != null) {
			setLatitude(latitude);
		}

		Double longitude = (Double)attributes.get("longitude");

		if (longitude != null) {
			setLongitude(longitude);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getVenueId() {
		return _venueId;
	}

	@Override
	public void setVenueId(long venueId) {
		_venueId = venueId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getAddress() {
		if (_address == null) {
			return StringPool.BLANK;
		}
		else {
			return _address;
		}
	}

	@Override
	public void setAddress(String address) {
		_address = address;
	}

	@JSON
	@Override
	public double getLatitude() {
		return _latitude;
	}

	@Override
	public void setLatitude(double latitude) {
		_latitude = latitude;
	}

	@JSON
	@Override
	public double getLongitude() {
		return _longitude;
	}

	@Override
	public void setLongitude(double longitude) {
		_longitude = longitude;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Venue.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Venue.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Venue toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Venue)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		VenueImpl venueImpl = new VenueImpl();

		venueImpl.setUuid(getUuid());
		venueImpl.setCompanyId(getCompanyId());
		venueImpl.setGroupId(getGroupId());
		venueImpl.setUserId(getUserId());
		venueImpl.setUserName(getUserName());
		venueImpl.setCreateDate(getCreateDate());
		venueImpl.setModifiedDate(getModifiedDate());
		venueImpl.setVenueId(getVenueId());
		venueImpl.setName(getName());
		venueImpl.setAddress(getAddress());
		venueImpl.setLatitude(getLatitude());
		venueImpl.setLongitude(getLongitude());

		venueImpl.resetOriginalValues();

		return venueImpl;
	}

	@Override
	public int compareTo(Venue venue) {
		long primaryKey = venue.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Venue)) {
			return false;
		}

		Venue venue = (Venue)obj;

		long primaryKey = venue.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		VenueModelImpl venueModelImpl = this;

		venueModelImpl._originalUuid = venueModelImpl._uuid;

		venueModelImpl._originalCompanyId = venueModelImpl._companyId;

		venueModelImpl._setOriginalCompanyId = false;

		venueModelImpl._originalGroupId = venueModelImpl._groupId;

		venueModelImpl._setOriginalGroupId = false;

		venueModelImpl._setModifiedDate = false;

		venueModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Venue> toCacheModel() {
		VenueCacheModel venueCacheModel = new VenueCacheModel();

		venueCacheModel.uuid = getUuid();

		String uuid = venueCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			venueCacheModel.uuid = null;
		}

		venueCacheModel.companyId = getCompanyId();

		venueCacheModel.groupId = getGroupId();

		venueCacheModel.userId = getUserId();

		venueCacheModel.userName = getUserName();

		String userName = venueCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			venueCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			venueCacheModel.createDate = createDate.getTime();
		}
		else {
			venueCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			venueCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			venueCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		venueCacheModel.venueId = getVenueId();

		venueCacheModel.name = getName();

		String name = venueCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			venueCacheModel.name = null;
		}

		venueCacheModel.address = getAddress();

		String address = venueCacheModel.address;

		if ((address != null) && (address.length() == 0)) {
			venueCacheModel.address = null;
		}

		venueCacheModel.latitude = getLatitude();

		venueCacheModel.longitude = getLongitude();

		return venueCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", venueId=");
		sb.append(getVenueId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", address=");
		sb.append(getAddress());
		sb.append(", latitude=");
		sb.append(getLatitude());
		sb.append(", longitude=");
		sb.append(getLongitude());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("bg.jug.workshop.liferay.cfp.model.Venue");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>venueId</column-name><column-value><![CDATA[");
		sb.append(getVenueId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>address</column-name><column-value><![CDATA[");
		sb.append(getAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>latitude</column-name><column-value><![CDATA[");
		sb.append(getLatitude());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>longitude</column-name><column-value><![CDATA[");
		sb.append(getLongitude());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Venue.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Venue.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _venueId;
	private String _name;
	private String _address;
	private double _latitude;
	private double _longitude;
	private long _columnBitmask;
	private Venue _escapedModel;
}
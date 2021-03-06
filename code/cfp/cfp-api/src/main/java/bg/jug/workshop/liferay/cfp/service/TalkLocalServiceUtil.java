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

package bg.jug.workshop.liferay.cfp.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Talk. This utility wraps
 * {@link bg.jug.workshop.liferay.cfp.service.impl.TalkLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TalkLocalService
 * @see bg.jug.workshop.liferay.cfp.service.base.TalkLocalServiceBaseImpl
 * @see bg.jug.workshop.liferay.cfp.service.impl.TalkLocalServiceImpl
 * @generated
 */
@ProviderType
public class TalkLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link bg.jug.workshop.liferay.cfp.service.impl.TalkLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addEventTalk(long eventId, long talkId) {
		getService().addEventTalk(eventId, talkId);
	}

	public static void addEventTalk(long eventId,
		bg.jug.workshop.liferay.cfp.model.Talk talk) {
		getService().addEventTalk(eventId, talk);
	}

	public static void addEventTalks(long eventId,
		java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> talks) {
		getService().addEventTalks(eventId, talks);
	}

	public static void addEventTalks(long eventId, long[] talkIds) {
		getService().addEventTalks(eventId, talkIds);
	}

	/**
	* Adds the talk to the database. Also notifies the appropriate model listeners.
	*
	* @param talk the talk
	* @return the talk that was added
	*/
	@Deprecated
	public static bg.jug.workshop.liferay.cfp.model.Talk addTalk(
		bg.jug.workshop.liferay.cfp.model.Talk talk) {
		return getService().addTalk(talk);
	}

	public static void clearEventTalks(long eventId) {
		getService().clearEventTalks(eventId);
	}

	public static int countGroupTalks(long companyId, long groupId) {
		return getService().countGroupTalks(companyId, groupId);
	}

	/**
	* Creates a new talk with the primary key. Does not add the talk to the database.
	*
	* @param talkId the primary key for the new talk
	* @return the new talk
	*/
	public static bg.jug.workshop.liferay.cfp.model.Talk createTalk(long talkId) {
		return getService().createTalk(talkId);
	}

	public static void deleteEventTalk(long eventId, long talkId) {
		getService().deleteEventTalk(eventId, talkId);
	}

	public static void deleteEventTalk(long eventId,
		bg.jug.workshop.liferay.cfp.model.Talk talk) {
		getService().deleteEventTalk(eventId, talk);
	}

	public static void deleteEventTalks(long eventId,
		java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> talks) {
		getService().deleteEventTalks(eventId, talks);
	}

	public static void deleteEventTalks(long eventId, long[] talkIds) {
		getService().deleteEventTalks(eventId, talkIds);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the talk with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param talkId the primary key of the talk
	* @return the talk that was removed
	* @throws PortalException if a talk with the primary key could not be found
	*/
	@Deprecated
	public static bg.jug.workshop.liferay.cfp.model.Talk deleteTalk(long talkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTalk(talkId);
	}

	/**
	* Deletes given talk, it's respective resource entity (if present) and structure link (if present).
	*
	* @param talkId the id of the talk to delete
	* @param serviceContext the context of the service request (generated by the caller from servlet or portlet requests)
	* @throws PortalException if any error occur
	*/
	public static bg.jug.workshop.liferay.cfp.model.Talk deleteTalk(
		long talkId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTalk(talkId, serviceContext);
	}

	/**
	* Deletes the talk from the database. Also notifies the appropriate model listeners.
	*
	* @param talk the talk
	* @return the talk that was removed
	*/
	@Deprecated
	public static bg.jug.workshop.liferay.cfp.model.Talk deleteTalk(
		bg.jug.workshop.liferay.cfp.model.Talk talk) {
		return getService().deleteTalk(talk);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link bg.jug.workshop.liferay.cfp.model.impl.TalkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link bg.jug.workshop.liferay.cfp.model.impl.TalkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static bg.jug.workshop.liferay.cfp.model.Talk fetchTalk(long talkId) {
		return getService().fetchTalk(talkId);
	}

	/**
	* Returns the talk matching the UUID and group.
	*
	* @param uuid the talk's UUID
	* @param groupId the primary key of the group
	* @return the matching talk, or <code>null</code> if a matching talk could not be found
	*/
	public static bg.jug.workshop.liferay.cfp.model.Talk fetchTalkByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchTalkByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the eventIds of the events associated with the talk.
	*
	* @param talkId the talkId of the talk
	* @return long[] the eventIds of events associated with the talk
	*/
	public static long[] getEventPrimaryKeys(long talkId) {
		return getService().getEventPrimaryKeys(talkId);
	}

	public static java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> getEventTalks(
		long eventId) {
		return getService().getEventTalks(eventId);
	}

	public static java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> getEventTalks(
		long eventId, int start, int end) {
		return getService().getEventTalks(eventId, start, end);
	}

	public static java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> getEventTalks(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<bg.jug.workshop.liferay.cfp.model.Talk> orderByComparator) {
		return getService().getEventTalks(eventId, start, end, orderByComparator);
	}

	public static int getEventTalksCount(long eventId) {
		return getService().getEventTalksCount(eventId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* Returns a list of all talks for given platform instance (company) and site (group)
	*
	* @param companyId the id of the portal instance (guarantees data sharding in multi-tenant environments)
	* @param groupId the id of the site (guarantees data sharding in multi-hosting environments)
	*/
	public static java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> getGroupTalks(
		long companyId, long groupId) {
		return getService().getGroupTalks(companyId, groupId);
	}

	/**
	* Returns a subset (page) of talks for given platform instance (company) and site (group)
	*
	* @param companyId the id of the portal instance (guarantees data sharding in multi-tenant environments)
	* @param groupId the id of the site (guarantees data sharding in multi-hosting environments)
	* @param start the record to start from
	* @param end the record to end at
	*/
	public static java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> getGroupTalks(
		long companyId, long groupId, int start, int end) {
		return getService().getGroupTalks(companyId, groupId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the talk with the primary key.
	*
	* @param talkId the primary key of the talk
	* @return the talk
	* @throws PortalException if a talk with the primary key could not be found
	*/
	public static bg.jug.workshop.liferay.cfp.model.Talk getTalk(long talkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTalk(talkId);
	}

	/**
	* Returns the talk matching the UUID and group.
	*
	* @param uuid the talk's UUID
	* @param groupId the primary key of the group
	* @return the matching talk
	* @throws PortalException if a matching talk could not be found
	*/
	public static bg.jug.workshop.liferay.cfp.model.Talk getTalkByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTalkByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the talks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link bg.jug.workshop.liferay.cfp.model.impl.TalkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of talks
	* @param end the upper bound of the range of talks (not inclusive)
	* @return the range of talks
	*/
	public static java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> getTalks(
		int start, int end) {
		return getService().getTalks(start, end);
	}

	/**
	* Returns all the talks matching the UUID and company.
	*
	* @param uuid the UUID of the talks
	* @param companyId the primary key of the company
	* @return the matching talks, or an empty list if no matches were found
	*/
	public static java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> getTalksByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getTalksByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of talks matching the UUID and company.
	*
	* @param uuid the UUID of the talks
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of talks
	* @param end the upper bound of the range of talks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching talks, or an empty list if no matches were found
	*/
	public static java.util.List<bg.jug.workshop.liferay.cfp.model.Talk> getTalksByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<bg.jug.workshop.liferay.cfp.model.Talk> orderByComparator) {
		return getService()
				   .getTalksByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of talks.
	*
	* @return the number of talks
	*/
	public static int getTalksCount() {
		return getService().getTalksCount();
	}

	public static boolean hasEventTalk(long eventId, long talkId) {
		return getService().hasEventTalk(eventId, talkId);
	}

	public static boolean hasEventTalks(long eventId) {
		return getService().hasEventTalks(eventId);
	}

	/**
	* This method creates a new talk or updates an existing one.
	* <p>
	* If there is {@code talkId} provided it updates the talk with that id. Otherwise it creates a new talk.
	* <p>
	* This method also creates/updates :
	* <ul>
	*     <li> respective {@link Resource} entity. This is needed to link the talk with the resource framework
	* which among other things is used to manage resources' permissions
	*  <li> respective {@link DDMStructureLink} entity. This is needed to indicate the talk uses given {@link DDMStructure}
	* to prevent the structure from being deleted when there are talks using it.
	* </ul>
	* <strong>NOTE: Important information for wrapping this method: </strong>
	* <ul>
	*     <li> it uses  {@code counterLocalService.increment(Talk.class.getName())} as opposite to {@code counterLocalService.increment()}.
	* Take that into account to avoid id collisions if you modify the talk creation process.
	* </ul>
	*
	* @param talkId the id of the talk to save. If provided the it MUST be positive value.
	* @param structureId the id of the {@code DDMStructure} that defines the structure of the data
	* @param data the talk's data in a form described by the respective {@code DDMStructure}
	* @param serviceContext the context of the service request (generated by the caller from servlet or portlet requests)
	* @throws IllegalArgumentException if {@code talkId} is provided but it is not a positive value
	* @throws NoSuchTalkException if {@code talkId} is provided but a talk with such id can not be found
	* @throws PortalException if any other error occur
	*/
	public static bg.jug.workshop.liferay.cfp.model.Talk saveTalk(
		java.util.Optional<java.lang.Long> talkId, java.lang.String title,
		long structureId, long templateId, java.lang.String data,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .saveTalk(talkId, title, structureId, templateId, data,
			serviceContext);
	}

	public static void setEventTalks(long eventId, long[] talkIds) {
		getService().setEventTalks(eventId, talkIds);
	}

	/**
	* Updates the talk in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param talk the talk
	* @return the talk that was updated
	* @throws UnsupportedOperationException
	*/
	@Deprecated
	public static bg.jug.workshop.liferay.cfp.model.Talk updateTalk(
		bg.jug.workshop.liferay.cfp.model.Talk talk)
		throws java.lang.UnsupportedOperationException {
		return getService().updateTalk(talk);
	}

	public static TalkLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TalkLocalService, TalkLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TalkLocalService.class);
}
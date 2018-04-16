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

package bg.jug.workshop.liferay.cfp.service.base;

import aQute.bnd.annotation.ProviderType;

import bg.jug.workshop.liferay.cfp.model.Event;
import bg.jug.workshop.liferay.cfp.service.EventLocalService;
import bg.jug.workshop.liferay.cfp.service.persistence.EventPersistence;
import bg.jug.workshop.liferay.cfp.service.persistence.TalkPersistence;
import bg.jug.workshop.liferay.cfp.service.persistence.VenuePersistence;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the event local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link bg.jug.workshop.liferay.cfp.service.impl.EventLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see bg.jug.workshop.liferay.cfp.service.impl.EventLocalServiceImpl
 * @see bg.jug.workshop.liferay.cfp.service.EventLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class EventLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements EventLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link bg.jug.workshop.liferay.cfp.service.EventLocalServiceUtil} to access the event local service.
	 */

	/**
	 * Adds the event to the database. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Event addEvent(Event event) {
		event.setNew(true);

		return eventPersistence.update(event);
	}

	/**
	 * Creates a new event with the primary key. Does not add the event to the database.
	 *
	 * @param eventId the primary key for the new event
	 * @return the new event
	 */
	@Override
	public Event createEvent(long eventId) {
		return eventPersistence.create(eventId);
	}

	/**
	 * Deletes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the event
	 * @return the event that was removed
	 * @throws PortalException if a event with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Event deleteEvent(long eventId) throws PortalException {
		return eventPersistence.remove(eventId);
	}

	/**
	 * Deletes the event from the database. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Event deleteEvent(Event event) {
		return eventPersistence.remove(event);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Event.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return eventPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link bg.jug.workshop.liferay.cfp.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return eventPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link bg.jug.workshop.liferay.cfp.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return eventPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return eventPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return eventPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Event fetchEvent(long eventId) {
		return eventPersistence.fetchByPrimaryKey(eventId);
	}

	/**
	 * Returns the event matching the UUID and group.
	 *
	 * @param uuid the event's UUID
	 * @param groupId the primary key of the group
	 * @return the matching event, or <code>null</code> if a matching event could not be found
	 */
	@Override
	public Event fetchEventByUuidAndGroupId(String uuid, long groupId) {
		return eventPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the event with the primary key.
	 *
	 * @param eventId the primary key of the event
	 * @return the event
	 * @throws PortalException if a event with the primary key could not be found
	 */
	@Override
	public Event getEvent(long eventId) throws PortalException {
		return eventPersistence.findByPrimaryKey(eventId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(eventLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Event.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("eventId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(eventLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Event.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("eventId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(eventLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Event.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("eventId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Event>() {
				@Override
				public void performAction(Event event)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						event);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(Event.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return eventLocalService.deleteEvent((Event)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return eventPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the events matching the UUID and company.
	 *
	 * @param uuid the UUID of the events
	 * @param companyId the primary key of the company
	 * @return the matching events, or an empty list if no matches were found
	 */
	@Override
	public List<Event> getEventsByUuidAndCompanyId(String uuid, long companyId) {
		return eventPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of events matching the UUID and company.
	 *
	 * @param uuid the UUID of the events
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching events, or an empty list if no matches were found
	 */
	@Override
	public List<Event> getEventsByUuidAndCompanyId(String uuid, long companyId,
		int start, int end, OrderByComparator<Event> orderByComparator) {
		return eventPersistence.findByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	 * Returns the event matching the UUID and group.
	 *
	 * @param uuid the event's UUID
	 * @param groupId the primary key of the group
	 * @return the matching event
	 * @throws PortalException if a matching event could not be found
	 */
	@Override
	public Event getEventByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return eventPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link bg.jug.workshop.liferay.cfp.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of events
	 */
	@Override
	public List<Event> getEvents(int start, int end) {
		return eventPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of events.
	 *
	 * @return the number of events
	 */
	@Override
	public int getEventsCount() {
		return eventPersistence.countAll();
	}

	/**
	 * Updates the event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Event updateEvent(Event event) {
		return eventPersistence.update(event);
	}

	/**
	 */
	@Override
	public void addTalkEvent(long talkId, long eventId) {
		talkPersistence.addEvent(talkId, eventId);
	}

	/**
	 */
	@Override
	public void addTalkEvent(long talkId, Event event) {
		talkPersistence.addEvent(talkId, event);
	}

	/**
	 */
	@Override
	public void addTalkEvents(long talkId, long[] eventIds) {
		talkPersistence.addEvents(talkId, eventIds);
	}

	/**
	 */
	@Override
	public void addTalkEvents(long talkId, List<Event> events) {
		talkPersistence.addEvents(talkId, events);
	}

	/**
	 */
	@Override
	public void clearTalkEvents(long talkId) {
		talkPersistence.clearEvents(talkId);
	}

	/**
	 */
	@Override
	public void deleteTalkEvent(long talkId, long eventId) {
		talkPersistence.removeEvent(talkId, eventId);
	}

	/**
	 */
	@Override
	public void deleteTalkEvent(long talkId, Event event) {
		talkPersistence.removeEvent(talkId, event);
	}

	/**
	 */
	@Override
	public void deleteTalkEvents(long talkId, long[] eventIds) {
		talkPersistence.removeEvents(talkId, eventIds);
	}

	/**
	 */
	@Override
	public void deleteTalkEvents(long talkId, List<Event> events) {
		talkPersistence.removeEvents(talkId, events);
	}

	/**
	 * Returns the talkIds of the talks associated with the event.
	 *
	 * @param eventId the eventId of the event
	 * @return long[] the talkIds of talks associated with the event
	 */
	@Override
	public long[] getTalkPrimaryKeys(long eventId) {
		return eventPersistence.getTalkPrimaryKeys(eventId);
	}

	/**
	 */
	@Override
	public List<Event> getTalkEvents(long talkId) {
		return talkPersistence.getEvents(talkId);
	}

	/**
	 */
	@Override
	public List<Event> getTalkEvents(long talkId, int start, int end) {
		return talkPersistence.getEvents(talkId, start, end);
	}

	/**
	 */
	@Override
	public List<Event> getTalkEvents(long talkId, int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return talkPersistence.getEvents(talkId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getTalkEventsCount(long talkId) {
		return talkPersistence.getEventsSize(talkId);
	}

	/**
	 */
	@Override
	public boolean hasTalkEvent(long talkId, long eventId) {
		return talkPersistence.containsEvent(talkId, eventId);
	}

	/**
	 */
	@Override
	public boolean hasTalkEvents(long talkId) {
		return talkPersistence.containsEvents(talkId);
	}

	/**
	 */
	@Override
	public void setTalkEvents(long talkId, long[] eventIds) {
		talkPersistence.setEvents(talkId, eventIds);
	}

	/**
	 * Returns the event local service.
	 *
	 * @return the event local service
	 */
	public EventLocalService getEventLocalService() {
		return eventLocalService;
	}

	/**
	 * Sets the event local service.
	 *
	 * @param eventLocalService the event local service
	 */
	public void setEventLocalService(EventLocalService eventLocalService) {
		this.eventLocalService = eventLocalService;
	}

	/**
	 * Returns the event persistence.
	 *
	 * @return the event persistence
	 */
	public EventPersistence getEventPersistence() {
		return eventPersistence;
	}

	/**
	 * Sets the event persistence.
	 *
	 * @param eventPersistence the event persistence
	 */
	public void setEventPersistence(EventPersistence eventPersistence) {
		this.eventPersistence = eventPersistence;
	}

	/**
	 * Returns the talk local service.
	 *
	 * @return the talk local service
	 */
	public bg.jug.workshop.liferay.cfp.service.TalkLocalService getTalkLocalService() {
		return talkLocalService;
	}

	/**
	 * Sets the talk local service.
	 *
	 * @param talkLocalService the talk local service
	 */
	public void setTalkLocalService(
		bg.jug.workshop.liferay.cfp.service.TalkLocalService talkLocalService) {
		this.talkLocalService = talkLocalService;
	}

	/**
	 * Returns the talk persistence.
	 *
	 * @return the talk persistence
	 */
	public TalkPersistence getTalkPersistence() {
		return talkPersistence;
	}

	/**
	 * Sets the talk persistence.
	 *
	 * @param talkPersistence the talk persistence
	 */
	public void setTalkPersistence(TalkPersistence talkPersistence) {
		this.talkPersistence = talkPersistence;
	}

	/**
	 * Returns the venue local service.
	 *
	 * @return the venue local service
	 */
	public bg.jug.workshop.liferay.cfp.service.VenueLocalService getVenueLocalService() {
		return venueLocalService;
	}

	/**
	 * Sets the venue local service.
	 *
	 * @param venueLocalService the venue local service
	 */
	public void setVenueLocalService(
		bg.jug.workshop.liferay.cfp.service.VenueLocalService venueLocalService) {
		this.venueLocalService = venueLocalService;
	}

	/**
	 * Returns the venue persistence.
	 *
	 * @return the venue persistence
	 */
	public VenuePersistence getVenuePersistence() {
		return venuePersistence;
	}

	/**
	 * Sets the venue persistence.
	 *
	 * @param venuePersistence the venue persistence
	 */
	public void setVenuePersistence(VenuePersistence venuePersistence) {
		this.venuePersistence = venuePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("bg.jug.workshop.liferay.cfp.model.Event",
			eventLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"bg.jug.workshop.liferay.cfp.model.Event");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return EventLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Event.class;
	}

	protected String getModelClassName() {
		return Event.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = eventPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = EventLocalService.class)
	protected EventLocalService eventLocalService;
	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;
	@BeanReference(type = bg.jug.workshop.liferay.cfp.service.TalkLocalService.class)
	protected bg.jug.workshop.liferay.cfp.service.TalkLocalService talkLocalService;
	@BeanReference(type = TalkPersistence.class)
	protected TalkPersistence talkPersistence;
	@BeanReference(type = bg.jug.workshop.liferay.cfp.service.VenueLocalService.class)
	protected bg.jug.workshop.liferay.cfp.service.VenueLocalService venueLocalService;
	@BeanReference(type = VenuePersistence.class)
	protected VenuePersistence venuePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}
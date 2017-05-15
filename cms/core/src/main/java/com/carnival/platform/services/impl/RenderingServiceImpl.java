package com.carnival.platform.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.models.component.AbstractComponentModel;
import com.carnival.platform.models.data.TitleDataModel;
import com.carnival.platform.services.NashornEngineService;
import com.carnival.platform.services.RenderingService;

/**
 * OSGI configuration for component rendering.
 * 
 * @author msajja
 *
 */
@Component(immediate = true, metatype = true, label = "Carnival - Rendering Service", description = "Configuration to control component rendering type as server side or client side")
@Service(value = RenderingService.class)
public class RenderingServiceImpl implements RenderingService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RenderingServiceImpl.class);

	/** The Constant RENDERING_TYPE. */
	@Property(unbounded = PropertyUnbounded.ARRAY, label = "Static Components", description = "List of static components with no client side behavior", value = {
			TitleDataModel.COMPONENT_NAME })
	static final String RENDERING_TYPE = "renderingType";

	/** The rendering types. */
	private List<String> renderingTypes;

	/** The nashorn engine service. */
	@Reference
	private NashornEngineService nashornEngineService;

	/**
	 * Activate.
	 *
	 * @param properties
	 *            the properties
	 */
	@Activate
	protected final void activate(final Map<String, String> properties) {
		String[] stringArray = PropertiesUtil.toStringArray(properties.get(RENDERING_TYPE), new String[0]);
		renderingTypes = Arrays.asList(stringArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carnival.platform.services.RenderingService#isComponentStatic(
	 * java.lang.String)
	 */
	@Override
	public boolean isComponentStatic(String componentName) {
		boolean isStatic = renderingTypes.contains(componentName);
		LOGGER.info("Is component: {} static?: {}", componentName, isStatic);
		return isStatic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carnival.platform.services.RenderingService#renderComponentHTML(com.
	 * carnival.platform.models.component.AbstractComponentModel)
	 */
	@Override
	public String renderComponentHTML(AbstractComponentModel slingModel) {

		Resource resource = slingModel.getResource();
		StringBuilder resourceTypePath = new StringBuilder(resource.getResourceType());

		// Checks if the resource type is absolute or relative
		if (resourceTypePath.charAt(0) != '/') {
			resourceTypePath.insert(0, "/apps/");
		}
		resourceTypePath.append("/index.js");
		// Generate the html from the nashorn engine
		String html = nashornEngineService.evaluateSSRComponentHTML(resourceTypePath.toString(), slingModel.getJson());
		LOGGER.debug("Component HTML for {} is {}", slingModel.getComponentName(), html);
		return html;
	}

}

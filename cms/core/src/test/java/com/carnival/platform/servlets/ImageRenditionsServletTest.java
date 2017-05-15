/**
 * 
 */
package com.carnival.platform.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.servlet.ServletOutputStream;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.service.component.ComponentContext;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.dam.api.RenditionPicker;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.designer.Designer;
import com.day.cq.wcm.commons.AbstractImageServlet;
import com.day.image.Layer;

import junitx.util.PrivateAccessor;

/**
 * @author ssahu6
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageRenditionsServletTest {
	
	private ImageRenditionsServlet imageRenditionsServlet = new ImageRenditionsServlet();
	
	@Mock
	private ComponentContext componentContext;
	
	@Mock
	private SlingHttpServletRequest request;
	
	@Mock
	private SlingHttpServletResponse response;
	
	@Mock
	private RequestPathInfo requestPathInfo;
	
	@Mock
	private Node node;
	
	@Mock
	private Resource resource;
	
	@Mock
	private Property property;
	
	@Mock
	private Asset asset;
	
	@Mock
	private Rendition rendition;
	
	@Mock
	private ResourceResolver resolver;
	
	@Mock
	private PageManager pageManager;
	
	@Mock
	private Designer designer;
	
	@Test
	public void testActivateWithValidDefaults() throws NoSuchFieldException {
		@SuppressWarnings("unchecked")
		Dictionary<String, Object> properties = mock(Dictionary.class);
		when(componentContext.getProperties()).thenReturn(properties);
		when(properties.get(Mockito.anyObject())).thenReturn(new Double(0.5D));
		imageRenditionsServlet.activate(componentContext);
		assertEquals(0.5, PrivateAccessor.getField(imageRenditionsServlet, "defaultImageQuality"));
	}

	@Test
	public void testActivateOursidePositiveDefaults() throws NoSuchFieldException {
		@SuppressWarnings("unchecked")
		Dictionary<String, Object> properties = mock(Dictionary.class);
		when(componentContext.getProperties()).thenReturn(properties);
		when(properties.get(Mockito.anyObject())).thenReturn(new Double(1.5D));
		imageRenditionsServlet.activate(componentContext);
		assertEquals(0.82, PrivateAccessor.getField(imageRenditionsServlet, "defaultImageQuality"));
	}

	@Test
	public void testActivateWithOursideNegativeDefaults() throws NoSuchFieldException {
		@SuppressWarnings("unchecked")
		Dictionary<String, Object> properties = mock(Dictionary.class);
		when(componentContext.getProperties()).thenReturn(properties);
		when(properties.get(Mockito.anyObject())).thenReturn(new Double(-1.5D));
		imageRenditionsServlet.activate(componentContext);
		assertEquals(0.82, PrivateAccessor.getField(imageRenditionsServlet, "defaultImageQuality"));
	}

	@Test
	public void testCreateLayerWithTwoSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "high"});
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@Test
	public void testCreateLayerWithThreeSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "750", "high"});
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@Test
	public void testCreateLayerWithFourSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "750", "200", "high"});
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@Test
	public void testCreateLayerWithFiveSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "750", "200", "100", "high"});
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@Test
	public void testCreateLayerInvalidSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "751", "200", "high"});
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@Test
	public void testCreateLayerNonNumericSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "abc", "xyz", "high"});
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@Test
	public void testCreateLayerNonNumericSelector2() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "200", "xyz", "high"});
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateLayerValidAssetWithSupportedExt() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "750", "200", "high" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		assertNotNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateLayerValidAssetWithUnSupportedExt() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "750", "200", "high" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		assertNotNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateLayerValidAssetWithRendition() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "750", "200", "high" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(asset.getRendition(anyString())).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateLayerValidAssetWithUnknownExt() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "750", "200", "high" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		when(requestPathInfo.getExtension()).thenReturn("eps");
		assertNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateLayerValidZeroWidthAsset() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "high" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		assertNotNull(imageRenditionsServlet.createLayer(imageContext));
	}
	
	@Test
	public void testWriteLayerWithNullLayerValidExt() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "high"});
		Layer layer = imageRenditionsServlet.createLayer(imageContext);
		imageRenditionsServlet.writeLayer(imageContext.request, response, imageContext, layer);
	}
	
	@Test
	public void testWriteLayerWithNullLayerInvalidExt() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(new String[] {"image", "high"});
		when(requestPathInfo.getExtension()).thenReturn("eps");
		Layer layer = imageRenditionsServlet.createLayer(imageContext);
		imageRenditionsServlet.writeLayer(imageContext.request, response, imageContext, layer);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testWriteLayerWithNullLayerDamRendition() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "750", "200", "high" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(asset.getRendition(anyString())).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		when(rendition.getStream()).thenReturn(getImageInputStream());
		when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
		Layer layer = imageRenditionsServlet.createLayer(imageContext);
		imageRenditionsServlet.writeLayer(imageContext.request, response, imageContext, layer);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testWriteLayerWithLayer() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "750", "200", "high" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		when(rendition.getStream()).thenReturn(getImageInputStream());
		when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
		Layer layer = imageRenditionsServlet.createLayer(imageContext);
		imageRenditionsServlet.writeLayer(imageContext.request, response, imageContext, layer);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testWriteLayerWithLayerThreeSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "750", "low" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		when(rendition.getStream()).thenReturn(getImageInputStream());
		when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
		Layer layer = imageRenditionsServlet.createLayer(imageContext);
		imageRenditionsServlet.writeLayer(imageContext.request, response, imageContext, layer);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testWriteLayerWithLayerTwoSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image", "medium" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		when(rendition.getStream()).thenReturn(getImageInputStream());
		when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
		Layer layer = imageRenditionsServlet.createLayer(imageContext);
		imageRenditionsServlet.writeLayer(imageContext.request, response, imageContext, layer);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testWriteLayerWithLayerOneSelector() throws RepositoryException, IOException, NoSuchFieldException {
		AbstractImageServlet.ImageContext imageContext = getImageContext(
				new String[] { "image" });
		when(resource.adaptTo(Asset.class)).thenReturn(asset);
		when(resolver.getResource(anyString())).thenReturn(resource);
		when(asset.getRendition(any(RenditionPicker.class))).thenReturn(rendition);
		when(rendition.adaptTo(Resource.class)).thenReturn(resource);
		when(resource.adaptTo(Property.class)).thenReturn(property);
		when(property.getStream()).thenReturn(getImageInputStream());
		when(rendition.getStream()).thenReturn(getImageInputStream());
		when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
		Layer layer = imageRenditionsServlet.createLayer(imageContext);
		imageRenditionsServlet.writeLayer(imageContext.request, response, imageContext, layer);
	}
	
	private InputStream getImageInputStream() throws IOException {
		int width = 750;
		int height = 200;
		BufferedImage image = new BufferedImage(750, 200, BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int a = (int) (Math.random() * 256); // alpha
				int r = (int) (Math.random() * 256); // red
				int g = (int) (Math.random() * 256); // green
				int b = (int) (Math.random() * 256); // blue

				int p = (a << 24) | (r << 16) | (g << 8) | b; // pixel

				image.setRGB(x, y, p);
			}
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, "jpg", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return new ByteArrayInputStream(imageInByte);
	}
	
	private AbstractImageServlet.ImageContext getImageContext(String[] selectors) throws NoSuchFieldException {
		when(resource.adaptTo(Node.class)).thenReturn(node);
		when(resolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		when(pageManager.getContainingPage(any(Resource.class))).thenReturn(null);
		when(resolver.adaptTo(Designer.class)).thenReturn(designer);
		when(designer.getDesign(any(Page.class))).thenReturn(null);
		when(resource.getResourceResolver()).thenReturn(resolver);
		when(requestPathInfo.getSelectors()).thenReturn(selectors);
		when(requestPathInfo.getExtension()).thenReturn("jpg");
		when(requestPathInfo.getResourcePath()).thenReturn("/content/dam/carnival/hal/hero.jpg");
		
		testActivateWithValidDefaults();
		
		AbstractImageServlet.ImageContext imageContext = new AbstractImageServlet.ImageContext(new MyRequest(request), "image/jpeg");
		return imageContext;
	}
	
	private class MyRequest extends SlingHttpServletRequestWrapper {
		Map<String, Object> attributes = new LinkedHashMap<>();
		public MyRequest(SlingHttpServletRequest wrappedRequest) {
			super(wrappedRequest);
		}
		@Override
		public RequestPathInfo getRequestPathInfo() {
			return requestPathInfo;
		}
		@Override
		public Resource getResource() {
			return resource;
		}
		@Override
		public ResourceResolver getResourceResolver() {
			return resolver;
		}
		@Override
		public Object getAttribute(String name) {
			return attributes.get(name);
		}
		@Override
		public void setAttribute(String name, Object o) {
			attributes.put(name, o);
		}
	}
}

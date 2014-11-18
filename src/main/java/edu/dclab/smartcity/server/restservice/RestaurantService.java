package edu.dclab.smartcity.server.restservice;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import edu.dclab.smartcity.server.dao.IBaseDao;
import edu.dclab.smartcity.server.entity.Restaurant;

@Service("RestaurantService")
@Path("/restaurant")
public class RestaurantService {
	
    @Resource(name = "baseDao")
    private IBaseDao<Restaurant> baseDao;
    
    public void setBaseDao(IBaseDao<Restaurant> baseDao) {
		this.baseDao = baseDao;
	}
    
    @Path("/add/{rId}")
	@GET
	//@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public JSONObject add(@PathParam("rId") long rId)
			throws Exception {
    	Restaurant rt = this.baseDao.queryById(Restaurant.class, rId);
    	if (rt == null) {
    		JSONObject jObject = new JSONObject();
			jObject.put("status", "object not found");
			return jObject;
    	}
    	
		this.baseDao.update(rt);
		JSONObject jObject = new JSONObject();
		jObject.put("status", "success");
		return jObject;
	}
}

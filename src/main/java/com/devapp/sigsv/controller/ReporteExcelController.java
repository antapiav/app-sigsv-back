package com.devapp.sigsv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.Pagination;
import com.devapp.sigsv.model.bean.Producto;
import com.devapp.sigsv.model.bean.ProductoCategoria;
import com.devapp.sigsv.model.bean.Proveedor;
import com.devapp.sigsv.model.bean.ProveedorCategoria;
import com.devapp.sigsv.model.bean.Stock;
import com.devapp.sigsv.model.bean.response.GenericResponse;
import com.devapp.sigsv.service.ClienteService;
import com.devapp.sigsv.service.CompraService;
import com.devapp.sigsv.service.ProductoCategoriaService;
import com.devapp.sigsv.service.ProductoService;
import com.devapp.sigsv.service.ProveedorCategoriaService;
import com.devapp.sigsv.service.ProveedorService;
import com.devapp.sigsv.service.VentaService;
import com.devapp.sigsv.util.AppConstantes;
import com.devapp.sigsv.util.AppUrl;
import com.devapp.sigsv.view.ClienteExcelView;
import com.devapp.sigsv.view.CompraExcelView;
import com.devapp.sigsv.view.ProductoCategoriaExcelView;
import com.devapp.sigsv.view.ProductoExcelView;
import com.devapp.sigsv.view.ProveedorCategoriaExcelView;
import com.devapp.sigsv.view.ProveedorExcelView;
import com.devapp.sigsv.view.VentaExcelView;

@CrossOrigin
@RestController
@RequestMapping(value = AppUrl.API_REPORTE_EXCEL)
public class ReporteExcelController extends AbstractController {
	public Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ClienteService clienteService;
	private ProveedorCategoriaService proveedorCategoriaService;
	private ProductoCategoriaService productoCategoriaService;
	private ProveedorService proveedorService;
	private ProductoService productoService;
	private CompraService compraService;
	private VentaService ventaService;
	
	@Autowired
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Autowired
	public void setProveedorCategoriaService(ProveedorCategoriaService proveedorCategoriaService) {
		this.proveedorCategoriaService = proveedorCategoriaService;
	}
	
	@Autowired
	public void setProductoCategoriaService(ProductoCategoriaService productoCategoriaService) {
		this.productoCategoriaService = productoCategoriaService;
	}
	
	@Autowired
	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}
	
	@Autowired
	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@Autowired
	public void setCompraService(CompraService compraService) {
		this.compraService = compraService;
	}
	
	@Autowired
	public void setVentaService(VentaService ventaService) {
		this.ventaService = ventaService;
	}
	
	@GetMapping(value = AppUrl.API_REPORTE_EXCEL_CLIENTE)
	public ModelAndView downloadReporteCliente(Pageable pageable, 
			@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value) {
		Map<String, Object> map = new HashMap<>();
		List<Cliente> list = clienteService.lstClientePaginado(pageable, name, operator, value).getContent();
		map.put(AppConstantes.EXCEL_TYPE, AppConstantes.FileType.XLSX);
		map.put(AppConstantes.EXCEL_FILENAME, AppConstantes.NAME_EXCEL_FILE_CLIENTE);
		map.put(AppConstantes.EXCEL_DOWNLOAD, true);
		map.put(AppConstantes.PARAM_PAGINATION, pageable);
		map.put(AppConstantes.PARAM_LIST, list);

		return new ModelAndView(new ClienteExcelView(messageSource), map);
	}
	
	@GetMapping(value = AppUrl.API_REPORTE_EXCEL_PROVEEDOR_CATEGORIA)
	public ModelAndView downloadReporteProveedorCategoria(Pageable pageable, 
			@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value) {
		Map<String, Object> map = new HashMap<>();
		List<ProveedorCategoria> list = proveedorCategoriaService.lstProveedorCategoriaPaginado(pageable, name, operator, value).getContent();
		map.put(AppConstantes.EXCEL_TYPE, AppConstantes.FileType.XLSX);
		map.put(AppConstantes.EXCEL_FILENAME, AppConstantes.NAME_EXCEL_FILE_CATEGORIA_DE_PROVEEDOR);
		map.put(AppConstantes.EXCEL_DOWNLOAD, true);
		map.put(AppConstantes.PARAM_PAGINATION, pageable);
		map.put(AppConstantes.PARAM_LIST, list);

		return new ModelAndView(new ProveedorCategoriaExcelView(messageSource), map);
	}
	
	@GetMapping(value = AppUrl.API_REPORTE_EXCEL_PRODUCTO_CATEGORIA)
	public ModelAndView downloadReporteProductoCategoria(Pageable pageable, 
			@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value) {
		Map<String, Object> map = new HashMap<>();
		List<ProductoCategoria> list = productoCategoriaService.lstProductoCategoriaPaginado(pageable, name, operator, value).getContent();
		map.put(AppConstantes.EXCEL_TYPE, AppConstantes.FileType.XLSX);
		map.put(AppConstantes.EXCEL_FILENAME, AppConstantes.NAME_EXCEL_FILE_CATEGORIA_PRODUCTO);
		map.put(AppConstantes.EXCEL_DOWNLOAD, true);
		map.put(AppConstantes.PARAM_PAGINATION, pageable);
		map.put(AppConstantes.PARAM_LIST, list);

		return new ModelAndView(new ProductoCategoriaExcelView(messageSource), map);
	}
	
	@GetMapping(value = AppUrl.API_REPORTE_EXCEL_PROVEEDOR)
	public ModelAndView downloadReporteProveedor(Pageable pageable, 
			@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value,
            @RequestParam(AppConstantes.ID_PROVEEDOR_CATEGORIA) String idProveedorCategoria) {
		Map<String, Object> map = new HashMap<>();
		List<Proveedor> list = proveedorService.lstProveedorPaginado(pageable, name, operator, value, idProveedorCategoria).getContent();
		map.put(AppConstantes.EXCEL_TYPE, AppConstantes.FileType.XLSX);
		map.put(AppConstantes.EXCEL_FILENAME, AppConstantes.NAME_EXCEL_FILE_PROVEEDOR);
		map.put(AppConstantes.EXCEL_DOWNLOAD, true);
		map.put(AppConstantes.PARAM_PAGINATION, pageable);
		map.put(AppConstantes.PARAM_LIST, list);

		return new ModelAndView(new ProveedorExcelView(messageSource), map);
	}
	
	@GetMapping(value = AppUrl.API_REPORTE_EXCEL_PRODUCTO)
	public ModelAndView downloadReporteProducto(Pageable pageable, 
			@RequestParam(AppConstantes.PARAM_PATH_NAME) String name,
            @RequestParam(AppConstantes.PARAM_PATH_OPERATOR) String operator,
            @RequestParam(AppConstantes.PARAM_PATH_VALUE) String value,
            @RequestParam(AppConstantes.ID_PRODUCTO_CATEGORIA) String idProductoCategoria) {
		Map<String, Object> map = new HashMap<>();
		List<Stock> list = productoService.lstProductoPaginado(pageable, name, operator, value, idProductoCategoria).getContent();
		map.put(AppConstantes.EXCEL_TYPE, AppConstantes.FileType.XLSX);
		map.put(AppConstantes.EXCEL_FILENAME, AppConstantes.NAME_EXCEL_FILE_PRODUCTO);
		map.put(AppConstantes.EXCEL_DOWNLOAD, true);
		map.put(AppConstantes.PARAM_PAGINATION, pageable);
		map.put(AppConstantes.PARAM_LIST, list);

		return new ModelAndView(new ProductoExcelView(messageSource), map);
	}
	
	@PostMapping(value = AppUrl.API_REPORTE_EXCEL_COMPRA)
	public ModelAndView downloadReporteCompra(@RequestBody Pagination pagination) {
		log.info("XXXXXXXXXXXX");
		Map<String, Object> map = new HashMap<>();
		GenericResponse<?> response = compraService.getLstCompraPaginado(pagination);
		map.put(AppConstantes.EXCEL_TYPE, AppConstantes.FileType.XLSX);
		map.put(AppConstantes.EXCEL_FILENAME, AppConstantes.NAME_EXCEL_FILE_COMPRA);
		map.put(AppConstantes.EXCEL_DOWNLOAD, true);
		map.put(AppConstantes.PARAM_PAGINATION, pagination);
		map.put(AppConstantes.PARAM_LIST, response);

		return new ModelAndView(new CompraExcelView(messageSource), map);
	}
	
	@PostMapping(value = AppUrl.API_REPORTE_EXCEL_VENTA)
	public ModelAndView downloadReporteVenta(@RequestBody Pagination pagination) {
		Map<String, Object> map = new HashMap<>();
		GenericResponse<?> response = ventaService.getLsVentaPaginado(pagination);
		map.put(AppConstantes.EXCEL_TYPE, AppConstantes.FileType.XLSX);
		map.put(AppConstantes.EXCEL_FILENAME, AppConstantes.NAME_EXCEL_FILE_VENTA);
		map.put(AppConstantes.EXCEL_DOWNLOAD, true);
		map.put(AppConstantes.PARAM_PAGINATION, pagination);
		map.put(AppConstantes.PARAM_LIST, response);

		return new ModelAndView(new VentaExcelView(messageSource), map);
	}

}

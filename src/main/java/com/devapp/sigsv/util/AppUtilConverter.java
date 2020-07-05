package com.devapp.sigsv.util;

import com.devapp.sigsv.model.bean.Cliente;
import com.devapp.sigsv.model.bean.Compra;
import com.devapp.sigsv.model.bean.DetalleCompra;
import com.devapp.sigsv.model.bean.DetalleCompraTemp;
import com.devapp.sigsv.model.bean.DetalleVenta;
import com.devapp.sigsv.model.bean.DetalleVentaTemp;
import com.devapp.sigsv.model.bean.Multitabla;
import com.devapp.sigsv.model.bean.Producto;
import com.devapp.sigsv.model.bean.ProductoCategoria;
import com.devapp.sigsv.model.bean.Proveedor;
import com.devapp.sigsv.model.bean.ProveedorCategoria;
import com.devapp.sigsv.model.bean.Stock;
import com.devapp.sigsv.model.bean.Usuario;
import com.devapp.sigsv.model.bean.Venta;
import com.devapp.sigsv.model.entity.SgvCliente;
import com.devapp.sigsv.model.entity.SgvCompra;
import com.devapp.sigsv.model.entity.SgvDetalleCompra;
import com.devapp.sigsv.model.entity.SgvDetalleCompraTemp;
import com.devapp.sigsv.model.entity.SgvDetalleVenta;
import com.devapp.sigsv.model.entity.SgvDetalleVentaTemp;
import com.devapp.sigsv.model.entity.SgvMultitabla;
import com.devapp.sigsv.model.entity.SgvProducto;
import com.devapp.sigsv.model.entity.SgvProductoCategoria;
import com.devapp.sigsv.model.entity.SgvProveedor;
import com.devapp.sigsv.model.entity.SgvProveedorCategoria;
import com.devapp.sigsv.model.entity.SgvStock;
import com.devapp.sigsv.model.entity.SgvUsuario;
import com.devapp.sigsv.model.entity.SgvVenta;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtilConverter {

    private static final Logger log = LoggerFactory.getLogger(AppUtilConverter.class);

    protected AppUtilConverter(){
        super();
    }

    public static Multitabla convertSgvMultitablaToMultitabla(SgvMultitabla sgvMultitabla){

        Multitabla multitabla = new Multitabla();
        if(sgvMultitabla != null){
            multitabla.setIdCodigo(sgvMultitabla.getIdCodigo());
			multitabla.setDescripcion(sgvMultitabla.getDescripcion());
            multitabla.setDescripcionValor(sgvMultitabla.getDescripcionValor());
            multitabla.setIdTabla(sgvMultitabla.getIdTabla());
            multitabla.setIdItem(sgvMultitabla.getIdItem());
            multitabla.setIndActivo(sgvMultitabla.getIndActivo());
        }
        return multitabla;
    }

    public static SgvMultitabla convertMultitablaToSgvMultitabla(Multitabla multitabla){
        SgvMultitabla sgvMultitabla = new SgvMultitabla();
        if(multitabla != null){
            sgvMultitabla.setIdCodigo(multitabla.getIdCodigo());
			sgvMultitabla.setDescripcion(multitabla.getDescripcion());
            sgvMultitabla.setDescripcionValor(multitabla.getDescripcionValor());
            sgvMultitabla.setIdTabla(multitabla.getIdTabla());
            sgvMultitabla.setIdItem(multitabla.getIdItem());
            sgvMultitabla.setIndActivo(multitabla.getIndActivo());
        }
        return sgvMultitabla;
    }

    public static Cliente convertSgvClienteToCliente(SgvCliente sgvCliente){
        Cliente cliente = new Cliente();
        if(sgvCliente != null){
            cliente.setIdCliente(sgvCliente.getIdCliente());
            cliente.setDni(sgvCliente.getDni());
            cliente.setNombre(sgvCliente.getNombre());
            cliente.setApPaterno(sgvCliente.getApPaterno());
            cliente.setApMaterno(sgvCliente.getApMaterno());
            cliente.setDireccion(sgvCliente.getDireccion());
            cliente.setContacto(sgvCliente.getContacto());
            cliente.setTelefono(sgvCliente.getTelefono());
            cliente.setEmail(sgvCliente.getEmail());
            cliente.setIndActivo(sgvCliente.getIndActivo());
        }
        return cliente;
    }

    public static SgvCliente convertClienteToSgvCliente(Cliente cliente){
        SgvCliente sgvCliente = new SgvCliente();
        if(cliente != null){
            sgvCliente.setIdCliente(cliente.getIdCliente());
            sgvCliente.setDni(cliente.getDni());
            sgvCliente.setNombre(cliente.getNombre());
            sgvCliente.setApPaterno(cliente.getApPaterno());
            sgvCliente.setApMaterno(cliente.getApMaterno());
            sgvCliente.setDireccion(cliente.getDireccion());
            sgvCliente.setContacto(cliente.getContacto());
            sgvCliente.setTelefono(cliente.getTelefono());
            sgvCliente.setEmail(cliente.getEmail());
            sgvCliente.setIndActivo(cliente.getIndActivo());
        }
        return sgvCliente;
    }

    public static Compra convertSgvCompraToCompra(SgvCompra sgvCompra){
        Compra compra = new Compra();
        if(sgvCompra != null){
            compra.setIdCompra(sgvCompra.getIdCompra());
            compra.setNumComprobante(sgvCompra.getNumComprobante());
            compra.setFecha(sgvCompra.getFecha());
            compra.setValorCompra(sgvCompra.getValorCompra());
            compra.setIgv(sgvCompra.getIgv());
            compra.setImporteTotal(sgvCompra.getImporteTotal());
            compra.setIndActivo(sgvCompra.getIndActivo());

            compra.setProveedor(convertSgvProveedorToProveedor(sgvCompra.getSgvProveedor()));
            //compra.setUsuario(convertSgvUsuarioToUsuario(sgvCompra.getSgvUsuario()));
            compra.setTipoComprobante(convertSgvMultitablaToMultitabla(sgvCompra.getSgvTipoComprobante()));
        }
        return compra;
    }

    public static SgvCompra convertCompraToSgvCompra(Compra compra){
        SgvCompra sgvCompra = new SgvCompra();
        if(compra != null){
            sgvCompra.setIdCompra(compra.getIdCompra());
            sgvCompra.setNumComprobante(compra.getNumComprobante());
            sgvCompra.setFecha(compra.getFecha());
            sgvCompra.setValorCompra(compra.getValorCompra());
            sgvCompra.setIgv(compra.getIgv());
            sgvCompra.setImporteTotal(compra.getImporteTotal());
            sgvCompra.setIndActivo(compra.getIndActivo());

            sgvCompra.setSgvProveedor(convertProveedorToSgvProveedor(compra.getProveedor()));
            //sgvCompra.setSgvUsuario(convertUsuarioToSgvUsuario(compra.getUsuario()));
            sgvCompra.setSgvTipoComprobante(convertMultitablaToSgvMultitabla(compra.getTipoComprobante()));
        }
        return sgvCompra;
    }

    public static DetalleCompra convertSgvDetalleCompraToDetalleCompra(SgvDetalleCompra sgvDetalleCompra){
        DetalleCompra detalleCompra = new DetalleCompra();
        if(sgvDetalleCompra != null){
            detalleCompra.setIdDetalleCompra(sgvDetalleCompra.getIdDetalleCompra());
            detalleCompra.setCantidad(sgvDetalleCompra.getCantidad());
            detalleCompra.setDescuento(sgvDetalleCompra.getDescuento());
            detalleCompra.setProducto(convertSgvProductoToProducto (sgvDetalleCompra.getSgvProducto()));
            detalleCompra.setCompra(convertSgvCompraToCompra(sgvDetalleCompra.getSgvCompra()));
            //detalleCompra.setUnidad(convertSgvMultitablaToMultitabla(sgvDetalleCompra.getSgvUnidad()));
        }
        return detalleCompra;
    }

    public static SgvDetalleCompra convertDetalleCompraToSgvDetalleCompra(DetalleCompra detalleCompra){
        SgvDetalleCompra sgvDetalleCompra = new SgvDetalleCompra();
        if(detalleCompra != null){
            sgvDetalleCompra.setIdDetalleCompra(detalleCompra.getIdDetalleCompra());
            sgvDetalleCompra.setCantidad(detalleCompra.getCantidad());
            sgvDetalleCompra.setDescuento(detalleCompra.getDescuento());
            sgvDetalleCompra.setSgvProducto(convertProductoToSgvProducto (detalleCompra.getProducto()));
            sgvDetalleCompra.setSgvCompra(convertCompraToSgvCompra(detalleCompra.getCompra()));
            //sgvDetalleCompra.setSgvUnidad(convertMultitablaToSgvMultitabla(detalleCompra.getUnidad()));
        }
        return sgvDetalleCompra;
    }

    public static DetalleCompraTemp convertSgvDetalleCompraTempToDetalleCompraTemp(SgvDetalleCompraTemp sgvDetalleCompraTemp){
        DetalleCompraTemp  detalleCompraTemp = new DetalleCompraTemp();
        if(sgvDetalleCompraTemp != null){
            detalleCompraTemp.setIdDetalleCompra(sgvDetalleCompraTemp.getIdDetalleCompra());
            detalleCompraTemp.setCantidad(sgvDetalleCompraTemp.getCantidad());
            detalleCompraTemp.setTotal(sgvDetalleCompraTemp.getTotal());
            detalleCompraTemp.setProducto(convertSgvProductoToProducto (sgvDetalleCompraTemp.getSgvProducto()));
            detalleCompraTemp.setCompra(convertSgvCompraToCompra(sgvDetalleCompraTemp.getSgvCompra()));
            detalleCompraTemp.setUnidad(convertSgvMultitablaToMultitabla(sgvDetalleCompraTemp.getSgvUnidad()));
        }
        return detalleCompraTemp;
    }

    public static SgvDetalleCompraTemp convertDetalleCompraTempToSgvDetalleCompraTemp(DetalleCompraTemp detalleCompraTemp){
        SgvDetalleCompraTemp sgvDetalleCompraTemp = new SgvDetalleCompraTemp();
        if(detalleCompraTemp != null){
            sgvDetalleCompraTemp.setIdDetalleCompra(detalleCompraTemp.getIdDetalleCompra());
            sgvDetalleCompraTemp.setCantidad(detalleCompraTemp.getCantidad());
            sgvDetalleCompraTemp.setTotal(detalleCompraTemp.getTotal());
            sgvDetalleCompraTemp.setSgvProducto(convertProductoToSgvProducto (detalleCompraTemp.getProducto()));
            sgvDetalleCompraTemp.setSgvCompra(convertCompraToSgvCompra(detalleCompraTemp.getCompra()));
            sgvDetalleCompraTemp.setSgvUnidad(convertMultitablaToSgvMultitabla(detalleCompraTemp.getUnidad()));
        }
        return sgvDetalleCompraTemp;
    }

    public static DetalleVenta convertSgvDetalleVentaToDetalleVenta(SgvDetalleVenta sgvDetalleVenta){
        DetalleVenta detalleVenta = new DetalleVenta();
        if(sgvDetalleVenta != null){
            detalleVenta.setIdDetalleVenta(sgvDetalleVenta.getIdDetalleVenta());
            detalleVenta.setCantidad(sgvDetalleVenta.getCantidad());
            detalleVenta.setProducto(convertSgvProductoToProducto (sgvDetalleVenta.getSgvProducto()));
            detalleVenta.setVenta(convertSgvVentaToVenta(sgvDetalleVenta.getSgvVenta()));
            detalleVenta.setDescuento(sgvDetalleVenta.getDescuento());
        }
        return detalleVenta;
    }

    public static SgvDetalleVenta convertDetalleVentaToSgvDetalleVenta(DetalleVenta detalleVenta){
        SgvDetalleVenta sgvDetalleVenta = new SgvDetalleVenta();
        if(detalleVenta != null){
            sgvDetalleVenta.setIdDetalleVenta(detalleVenta.getIdDetalleVenta());
            sgvDetalleVenta.setCantidad(detalleVenta.getCantidad());
            sgvDetalleVenta.setSgvProducto(convertProductoToSgvProducto (detalleVenta.getProducto()));
            sgvDetalleVenta.setSgvVenta(convertVentaToSgvVenta(detalleVenta.getVenta()));
            sgvDetalleVenta.setDescuento(detalleVenta.getDescuento());
        }
        return sgvDetalleVenta;
    }

    public static DetalleVentaTemp convertSgvDetalleVentaTempToDetalleVentaTemp(SgvDetalleVentaTemp sgvDetalleVentaTemp){
        DetalleVentaTemp detalleVentaTemp = new DetalleVentaTemp();
        if(sgvDetalleVentaTemp != null){
            detalleVentaTemp.setIdDetalleVenta(sgvDetalleVentaTemp.getIdDetalleVenta());
            detalleVentaTemp.setCantidad(sgvDetalleVentaTemp.getCantidad());
            detalleVentaTemp.setTotal(sgvDetalleVentaTemp.getTotal());
            detalleVentaTemp.setProducto(convertSgvProductoToProducto (sgvDetalleVentaTemp.getSgvProducto()));
            detalleVentaTemp.setVenta(convertSgvVentaToVenta(sgvDetalleVentaTemp.getSgvVenta()));
            detalleVentaTemp.setUnidad(convertSgvMultitablaToMultitabla(sgvDetalleVentaTemp.getSgvUnidad()));
        }
        return detalleVentaTemp;
    }

    public static SgvDetalleVentaTemp convertDetalleVentaTempToSgvDetalleVentaTemp(DetalleVentaTemp detalleVentaTemp){
        SgvDetalleVentaTemp sgvDetalleVentaTemp = new SgvDetalleVentaTemp();
        if(detalleVentaTemp != null){
            sgvDetalleVentaTemp.setIdDetalleVenta(detalleVentaTemp.getIdDetalleVenta());
            sgvDetalleVentaTemp.setCantidad(detalleVentaTemp.getCantidad());
            sgvDetalleVentaTemp.setTotal(detalleVentaTemp.getTotal());
            sgvDetalleVentaTemp.setSgvProducto(convertProductoToSgvProducto (detalleVentaTemp.getProducto()));
            sgvDetalleVentaTemp.setSgvVenta(convertVentaToSgvVenta(detalleVentaTemp.getVenta()));
            sgvDetalleVentaTemp.setSgvUnidad(convertMultitablaToSgvMultitabla(detalleVentaTemp.getUnidad()));
        }
        return sgvDetalleVentaTemp;
    }

    public static Producto convertSgvProductoToProducto(SgvProducto sgvProducto){
        Producto producto = new Producto();
        if(sgvProducto != null){
            producto.setIdProducto(sgvProducto.getIdProducto());
            producto.setNombre(sgvProducto.getNombre());
            producto.setDetalle(sgvProducto.getDetalle());
            producto.setCodigo(sgvProducto.getCodigo());
            producto.setPreCosto(sgvProducto.getPreCosto());
            producto.setPreVenta(sgvProducto.getPreVenta());
            producto.setUtilidad(sgvProducto.getUtilidad());
            producto.setIndActivo(sgvProducto.getIndActivo());
            producto.setProductoCategoria(convertSgvProductoCategoriaToProductoCategoria(sgvProducto.getSgvProductoCategoria()));
            producto.setUnidad(convertSgvMultitablaToMultitabla(sgvProducto.getSgvUnidad()));
        }
        return producto;
    }

    public static SgvProducto convertProductoToSgvProducto(Producto producto){
        SgvProducto sgvProducto = new SgvProducto();
        if(producto != null){
            sgvProducto.setIdProducto(producto.getIdProducto());
            sgvProducto.setNombre(producto.getNombre());
            sgvProducto.setDetalle(producto.getDetalle());
            sgvProducto.setCodigo(producto.getCodigo());
            sgvProducto.setPreCosto(producto.getPreCosto());
            sgvProducto.setPreVenta(producto.getPreVenta());
            sgvProducto.setUtilidad(producto.getUtilidad());
            sgvProducto.setIndActivo(producto.getIndActivo());
            /*sgvProducto.setSgvDetalleCompra(convertDetalleCompraToSgvDetalleCompra(producto.getDetalleCompra()));
            sgvProducto.setSgvDetalleVenta(convertDetalleVentaToSgvDetalleVenta(producto.getDetalleVenta()));
            sgvProducto.setSgvStock(convertStockToSgvStock(producto.getStock()));*/
            sgvProducto.setSgvProductoCategoria(convertProductoCategoriaToSgvProductoCategoria(producto.getProductoCategoria()));
            sgvProducto.setSgvUnidad(convertMultitablaToSgvMultitabla(producto.getUnidad()));
        }
        return sgvProducto;
    }

    public static ProductoCategoria convertSgvProductoCategoriaToProductoCategoria(SgvProductoCategoria sgvProductoCategoria){
        ProductoCategoria productoCategoria = new ProductoCategoria();
        if(sgvProductoCategoria != null){
            productoCategoria.setIdProductoCategoria(sgvProductoCategoria.getIdProductoCategoria());
            productoCategoria.setNombre(sgvProductoCategoria.getNombre());
            productoCategoria.setDetalle(sgvProductoCategoria.getDetalle());
            productoCategoria.setIndActivo(sgvProductoCategoria.getIndActivo());
        }
        return productoCategoria;
    }

    public static SgvProductoCategoria convertProductoCategoriaToSgvProductoCategoria(ProductoCategoria productoCategoria){
        SgvProductoCategoria sgvProductoCategoria = new SgvProductoCategoria();
        if(productoCategoria != null){
            sgvProductoCategoria.setIdProductoCategoria(productoCategoria.getIdProductoCategoria());
            sgvProductoCategoria.setNombre(productoCategoria.getNombre());
            sgvProductoCategoria.setDetalle(productoCategoria.getDetalle());
            sgvProductoCategoria.setIndActivo(productoCategoria.getIndActivo());
        }
        return sgvProductoCategoria;
    }

    public static Proveedor convertSgvProveedorToProveedor(SgvProveedor sgvProveedor){
        Proveedor proveedor = new Proveedor();
        if(sgvProveedor != null){
            proveedor.setIdProveedor(sgvProveedor.getIdProveedor());
            proveedor.setNumDocumento(sgvProveedor.getNumDocumento());
            proveedor.setNombre(sgvProveedor.getNombre());
            proveedor.setDireccion(sgvProveedor.getDireccion());
            proveedor.setContacto(sgvProveedor.getContacto());
            proveedor.setTelefono(sgvProveedor.getTelefono());
            proveedor.setEmail(sgvProveedor.getEmail());
            proveedor.setIndActivo(sgvProveedor.getIndActivo());
            proveedor.setTipoDocumento(convertSgvMultitablaToMultitabla(sgvProveedor.getSgvTipoDocumento()));
            proveedor.setProveedorCategoria(convertSgvProveedorCategoriaToProveedorCategoria(sgvProveedor.getSgvProveedorcategoria()));
        }
        return proveedor;
    }

    public static SgvProveedor convertProveedorToSgvProveedor(Proveedor proveedor){
        SgvProveedor sgvProveedor = new SgvProveedor();
        if(proveedor != null){
            sgvProveedor.setIdProveedor(proveedor.getIdProveedor());
            sgvProveedor.setNumDocumento(proveedor.getNumDocumento());
            sgvProveedor.setNombre(proveedor.getNombre());
            sgvProveedor.setDireccion(proveedor.getDireccion());
            sgvProveedor.setContacto(proveedor.getContacto());
            sgvProveedor.setTelefono(proveedor.getTelefono());
            sgvProveedor.setEmail(proveedor.getEmail());
            sgvProveedor.setIndActivo(proveedor.getIndActivo());
            sgvProveedor.setSgvTipoDocumento(convertMultitablaToSgvMultitabla(proveedor.getTipoDocumento()));
            sgvProveedor.setSgvProveedorcategoria(convertProveedorCategoriaToSgvProveedorCategoria(proveedor.getProveedorCategoria()));
        }
        return sgvProveedor;
    }

    public static ProveedorCategoria convertSgvProveedorCategoriaToProveedorCategoria(SgvProveedorCategoria sgvProveedorCategoria){
        ProveedorCategoria proveedorCategoria = new ProveedorCategoria();
        if(sgvProveedorCategoria != null){
            proveedorCategoria.setIdProveedorCategoria(sgvProveedorCategoria.getIdProveedorCategoria());
            proveedorCategoria.setNombre(sgvProveedorCategoria.getNombre());
            proveedorCategoria.setDetalle(sgvProveedorCategoria.getDetalle());
            proveedorCategoria.setIndActivo(sgvProveedorCategoria.getIndActivo());
        }
        return proveedorCategoria;
    }

    public static SgvProveedorCategoria convertProveedorCategoriaToSgvProveedorCategoria(ProveedorCategoria proveedorCategoria){
        SgvProveedorCategoria sgvProveedorCategoria = new SgvProveedorCategoria();
        if(proveedorCategoria != null){
            sgvProveedorCategoria.setIdProveedorCategoria(proveedorCategoria.getIdProveedorCategoria());
            sgvProveedorCategoria.setNombre(proveedorCategoria.getNombre());
            sgvProveedorCategoria.setDetalle(proveedorCategoria.getDetalle());
            sgvProveedorCategoria.setIndActivo(proveedorCategoria.getIndActivo());
        }
        return sgvProveedorCategoria;
    }

    public static Stock convertSgvStockToStock(SgvStock sgvStock){
        Stock stock = new Stock();
        if(sgvStock != null){
            stock.setIdStock(sgvStock.getIdStock());
            stock.setStockComprado(sgvStock.getStockComprado());
            stock.setStockVendido(sgvStock.getStockVendido());    
            stock.setProducto(convertSgvProductoToProducto(sgvStock.getSgvProducto()));
        }
        return stock;
    }

    public static SgvStock convertStockToSgvStock(Stock stock){
        SgvStock sgvStock = new SgvStock();
        if(stock != null){ 
            sgvStock.setIdStock(stock.getIdStock());
            sgvStock.setStockComprado(stock.getStockComprado());
            sgvStock.setStockVendido(stock.getStockVendido());    
            sgvStock.setSgvProducto(convertProductoToSgvProducto(stock.getProducto()));
        }
        return sgvStock;
    }

    public static Usuario convertSgvUsuarioToUsuario(SgvUsuario sgvUsuario){
        Usuario usuario = new Usuario();
        if(sgvUsuario != null){
            usuario.setIdUsuario(sgvUsuario.getIdUsuario());
            usuario.setNombre(sgvUsuario.getNombre());
            usuario.setApPaterno(sgvUsuario.getApPatenro());
            usuario.setApMaterno(sgvUsuario.getApMaterno());
            usuario.setDni(sgvUsuario.getDni());
            usuario.setIndActivo(sgvUsuario.getIndActivo());
            usuario.setClave(sgvUsuario.getClave());
            usuario.setTipoUsuario(convertSgvMultitablaToMultitabla(sgvUsuario.getSgvTipoUsuario()));
        }
        return usuario;
    }

    public static SgvUsuario convertUsuarioToSgvUsuario(Usuario usuario){
        SgvUsuario sgvUsuario = new SgvUsuario();
        if(usuario != null){
            sgvUsuario.setIdUsuario(usuario.getIdUsuario());
            sgvUsuario.setNombre(usuario.getNombre());
            sgvUsuario.setApPatenro(usuario.getApPaterno());
            sgvUsuario.setApMaterno(usuario.getApMaterno());
            sgvUsuario.setDni(usuario.getDni());
            sgvUsuario.setIndActivo(usuario.getIndActivo());
            sgvUsuario.setClave(usuario.getClave());
            sgvUsuario.setSgvTipoUsuario(convertMultitablaToSgvMultitabla(usuario.getTipoUsuario()));
        }
        return sgvUsuario;
    }

    public static Venta convertSgvVentaToVenta(SgvVenta sgvVenta){
        Venta venta = new Venta();
        if(sgvVenta != null){ 
            venta.setIdVenta(sgvVenta.getIdVenta());
            venta.setTipoComprobante(convertSgvMultitablaToMultitabla(sgvVenta.getSgvTipoComprobante()));
            venta.setNumComprobante(sgvVenta.getNumComprobante());
            venta.setFecha(sgvVenta.getFecha());
            venta.setCliente(convertSgvClienteToCliente(sgvVenta.getSgvCliente()));
            venta.setDescuentoVenta(sgvVenta.getDescuentoVenta());
            venta.setMontoImpuestoBolsa(sgvVenta.getMontoImpuestoBolsa());
            venta.setMontoPagado(sgvVenta.getMontoPagado());
            venta.setValorVenta(sgvVenta.getValorVenta());
            venta.setIgv(sgvVenta.getIgv());
            venta.setImporteTotal(sgvVenta.getImporteTotal());
            venta.setIndActivo(sgvVenta.getIndActivo());
            venta.setCantBolsa(sgvVenta.getCantBolsa());
            //venta.setUsuario(convertSgvUsuarioToUsuario(sgvVenta.getSgvUsuario()));
            //venta.setMontoBolsa(convertSgvMultitablaToMultitabla(sgvVenta.getSgvMontoBolsa()));
        }
        return venta;
    }

    public static SgvVenta convertVentaToSgvVenta(Venta venta){
        SgvVenta sgvVenta = new SgvVenta();
        if(venta != null){
            sgvVenta.setIdVenta(venta.getIdVenta());
            sgvVenta.setSgvTipoComprobante(convertMultitablaToSgvMultitabla(venta.getTipoComprobante()));
            sgvVenta.setNumComprobante(venta.getNumComprobante());
            sgvVenta.setFecha(venta.getFecha());
            sgvVenta.setSgvCliente(convertClienteToSgvCliente(venta.getCliente()));
            sgvVenta.setDescuentoVenta(venta.getDescuentoVenta());
            sgvVenta.setMontoImpuestoBolsa(venta.getMontoImpuestoBolsa());
            sgvVenta.setMontoPagado(venta.getMontoPagado());
            sgvVenta.setValorVenta(venta.getValorVenta());            
            sgvVenta.setIgv(venta.getIgv());
            sgvVenta.setImporteTotal(venta.getImporteTotal());
            sgvVenta.setIndActivo(venta.getIndActivo());
            sgvVenta.setCantBolsa(venta.getCantBolsa());
            //sgvVenta.setSgvUsuario(convertUsuarioToSgvUsuario(venta.getUsuario()));
            //sgvVenta.setSgvMontoBolsa(convertMultitablaToSgvMultitabla(venta.getMontoBolsa()));
        }
        return sgvVenta;
    }
    
    public static String dateMilisecondsToDate(Date timeStamp) {
    	Calendar calendar = Calendar.getInstance();
    	StringBuilder strDate = new StringBuilder(); 
    	calendar.setTime(timeStamp);

    	int mYear = calendar.get(Calendar.YEAR);
    	int mMonth = calendar.get(Calendar.MONTH)+1;
    	int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    	
    	strDate.append(mDay);
    	strDate.append(AppConstantes.STRING_SLASH);
    	strDate.append(mMonth);
    	strDate.append(AppConstantes.STRING_SLASH);
    	strDate.append(mYear);
    	
    	return strDate.toString();
    }
    
    /**
     * Convierte el número que recibe como argumento a su representación escrita con letra.
     *
     * @param s Una cadena de texto que contiene los dígitos de un número.
     * @return  Una cadena de texto que contiene la representación con letra de
     *          la parte entera del número que se recibió como argumento.
     */
    public static String cantidadConLetra(String s) {
        StringBuilder result = new StringBuilder();
        BigDecimal totalBigDecimal = new BigDecimal(s).setScale(2, BigDecimal.ROUND_DOWN);
        long parteEntera = totalBigDecimal.toBigInteger().longValue();
        int triUnidades      = (int)((parteEntera % 1000));
        int triMiles         = (int)((parteEntera / 1000) % 1000);
        int triMillones      = (int)((parteEntera / 1000000) % 1000);
        int triMilMillones   = (int)((parteEntera / 1000000000) % 1000);
 
        if (parteEntera == 0) {
            result.append("Cero ");
            return result.toString();
        }
 
        if (triMilMillones > 0) result.append(triTexto(triMilMillones).toString() + "Mil ");
        if (triMillones > 0)    result.append(triTexto(triMillones).toString());
 
        if (triMilMillones == 0 && triMillones == 1) result.append("Millón ");
        else if (triMilMillones > 0 || triMillones > 0) result.append("Millones ");
 
        if (triMiles > 0)       result.append(triTexto(triMiles).toString() + "Mil ");
        if (triUnidades > 0)    result.append(triTexto(triUnidades).toString());
 
        return result.toString();
    }
 
    /**
     * Convierte una cantidad de tres cifras a su representación escrita con letra.
     *
     * @param n La cantidad a convertir.
     * @return  Una cadena de texto que contiene la representación con letra
     *          del número que se recibió como argumento.
     */
    private static StringBuilder triTexto(int n) {
        StringBuilder result = new StringBuilder();
        int centenas = n / 100;
        int decenas  = (n % 100) / 10;
        int unidades = (n % 10);
 
        switch (centenas) {
            case 0: break;
            case 1:
                if (decenas == 0 && unidades == 0) {
                    result.append("Cien ");
                    return result;
                }
                else result.append("Ciento ");
                break;
            case 2: result.append("Doscientos "); break;
            case 3: result.append("Trescientos "); break;
            case 4: result.append("Cuatrocientos "); break;
            case 5: result.append("Quinientos "); break;
            case 6: result.append("Seiscientos "); break;
            case 7: result.append("Setecientos "); break;
            case 8: result.append("Ochocientos "); break;
            case 9: result.append("Novecientos "); break;
        }
 
        switch (decenas) {
            case 0: break;
            case 1:
                if (unidades == 0) { result.append("Diez "); return result; }
                else if (unidades == 1) { result.append("Once "); return result; }
                else if (unidades == 2) { result.append("Doce "); return result; }
                else if (unidades == 3) { result.append("Trece "); return result; }
                else if (unidades == 4) { result.append("Catorce "); return result; }
                else if (unidades == 5) { result.append("Quince "); return result; }
                else result.append("Dieci");
                break;
            case 2:
                if (unidades == 0) { result.append("Veinte "); return result; }
                else result.append("Veinti");
                break;
            case 3: result.append("Treinta "); break;
            case 4: result.append("Cuarenta "); break;
            case 5: result.append("Cincuenta "); break;
            case 6: result.append("Sesenta "); break;
            case 7: result.append("Setenta "); break;
            case 8: result.append("Ochenta "); break;
            case 9: result.append("Noventa "); break;
        }
 
        if (decenas > 2 && unidades > 0)
            result.append("y ");
 
        switch (unidades) {
            case 0: break;
            case 1: result.append("Un "); break;
            case 2: result.append("Dos "); break;
            case 3: result.append("Tres "); break;
            case 4: result.append("Cuatro "); break;
            case 5: result.append("Cinco "); break;
            case 6: result.append("Seis "); break;
            case 7: result.append("Siete "); break;
            case 8: result.append("Ocho "); break;
            case 9: result.append("Nueve "); break;
        }
 
        return result;
    }
    
}
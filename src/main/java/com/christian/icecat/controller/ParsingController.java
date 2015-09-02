package com.christian.icecat.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.index.IcecatFile;
import com.christian.icecat.model.index.IcecatFileWrapper;
import com.christian.icecat.model.product.ICECATInterface;
import com.christian.icecat.model.product.Product;
import com.christian.icecat.parser.IcecatIndexParser;
import com.christian.icecat.parser.IcecatProductParser;
import java.util.Arrays;

/**
 * Class that have all the actions to parse the information gotten from Icecat.
 *
 * @author christian
 *
 */
public class ParsingController {

    private AbstractIcecatConnector connector;
    private IcecatIndexParser indexParser;
    private IcecatProductParser productParser;

    public static List<String> catList = Arrays.asList("364", "219", "1508", "1020");

    public ParsingController(AbstractIcecatConnector connector) {
        this.connector = connector;
    }

    public Map<Long, String> getAllProductsId() throws IcecatConnectionException, IcecatParsingException {
        Map<Long, String> productsMap = new TreeMap<>();

        IcecatFileWrapper fileWrapper = getIndexParser().parseStream(IcecatFileWrapper.class);
        List<IcecatFile> icecatFiles = fileWrapper.getFiles();
        for (IcecatFile file : icecatFiles) {
            /*NADIM --> This part will filter products by category ID*/
            //System.out.println("Category ID: " + file.getCatId());

            if (catList.contains(file.getCatId())) {
                productsMap.put(file.getProductId(), file.getUpdate());
            }
            /* END NADIM*/
        }

        return productsMap;
    }

    public Product getIcecatProduct(long id) throws IcecatConnectionException, IcecatParsingException {
        IcecatProductParser parser = getProductParser().cloneProductParser();

        parser.setProductId(id);
        ICECATInterface icecatInterface = parser.parseStream(ICECATInterface.class);

        return icecatInterface.getProduct();
    }

    public IcecatIndexParser getIndexParser() {
        if (indexParser == null) {
            setIndexParser(new IcecatIndexParser(connector));
        }
        return indexParser;
    }

    public void setIndexParser(IcecatIndexParser indexParser) {
        this.indexParser = indexParser;
    }

    public IcecatProductParser getProductParser() {
        if (productParser == null) {
            setProductParser(new IcecatProductParser(connector));
        }
        return productParser;
    }

    public void setProductParser(IcecatProductParser productParser) {
        this.productParser = productParser;
    }
    /* NADIM --> Added category id*/

    public static List<String> getCatList() {
        return catList;
    }

    public static void setCatList(List<String> catList) {
        ParsingController.catList = catList;
    }
    /*END NADIM*/
}

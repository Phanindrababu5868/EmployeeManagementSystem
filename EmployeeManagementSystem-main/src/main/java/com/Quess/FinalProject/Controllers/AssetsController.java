package com.Quess.FinalProject.Controllers;

import com.Quess.FinalProject.Exception.ResourceNotFoundException;
import com.Quess.FinalProject.Model.Assets;
import com.Quess.FinalProject.Service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/AssetsDetails")
public class AssetsController {
    @Autowired
    private AssetsService assetsService;

    public AssetsController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }


    @RequestMapping(value = "/Assets/post",method = RequestMethod.POST)
    public ResponseEntity<String> saveAssets(@RequestBody @Valid Assets assets)
    {
        assetsService.saveAsset(assets);
        return new ResponseEntity<String>("Asset record created successfully", HttpStatus.CREATED);

    }

    @RequestMapping(value = "/Assets/getall",method = RequestMethod.GET)
    public List<Assets> getAllAssets()
    {
        return assetsService.getAllAssets();
    }


    @RequestMapping(value = "/Assets/getbyid/{id}",method = {RequestMethod.GET})
    public ResponseEntity<Assets> getAssetById(@PathVariable("id") int id)
    {
        return new ResponseEntity<Assets>(assetsService.getDataBYId(id),HttpStatus.OK);
    }


    @RequestMapping(value = "/Assets/put/{id}",method = {RequestMethod.PUT})
    public ResponseEntity<String > updateAssesta(@PathVariable("id")int id, @Valid @RequestBody Assets Data)
    {
        assetsService.updateData(Data,id);
        return new ResponseEntity<String>("Asset Record Updated Successfully", HttpStatus.OK);

    }

    @RequestMapping(value = "/Assets/delete/{id}",method = {RequestMethod.DELETE})
    public ResponseEntity<String> deleteData( @Valid @PathVariable("id")int id)
    {
        assetsService.deleteAssets(id);
        return new ResponseEntity<String>("Asset Record deleted successfully",HttpStatus.OK);
    }
}

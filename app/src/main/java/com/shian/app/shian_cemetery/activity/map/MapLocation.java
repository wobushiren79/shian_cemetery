package com.shian.app.shian_cemetery.activity.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.view.dataview.cemetery.MapSelectViewNormal;
import com.shian.app.shian_cemetery.view.dialog.CustomDialog;


import java.util.List;

public class MapLocation extends BaseActivity implements BaiduMap.OnMapClickListener {

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    PoiSearch poiSearch;
    String locationPoint = AppData.LOCAL_ADDRESS;

    String longitude = "";
    String latitude = "";

    int numView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);
        numView = getIntent().getIntExtra(IntentName.INTENT_LOCATION_NUMVIEW, 1);
        init();
        //初始化控件
        initView();
        //初始化地图
        initMap();
        //去掉百度图标
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }


    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapClickListener(this);
    }

    private void init() {
        setTitle("选择位置");
    }


    private void initMap() {
        longitude = AppData.LOCAL_latitude + "";
        latitude = AppData.LOCAL_longitude + "";

        setCenter(AppData.LOCAL_latitude, AppData.LOCAL_longitude);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(poiListener);

        LatLng point = new LatLng(AppData.LOCAL_latitude, AppData.LOCAL_longitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.zhy_map_point_2);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);

//        poiSearch.searchInCity((new PoiCitySearchOption())
//                .city("成都")
//                .keyword(locationPoint));
    }

    private void setCenter(double latitude, double longitude) {
        //设定中心点坐标
        LatLng cenpt = new LatLng(latitude, longitude);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
        public void onGetPoiResult(PoiResult result) {
            //获取POI检索结果
            List<PoiInfo> poiInfos = result.getAllPoi();
            mBaiduMap.clear();
            if (poiInfos != null) {
                for (PoiInfo poiInfo : poiInfos) {
                    LatLng point = poiInfo.location;
                    //构建Marker图标
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.zhy_map_point_2);
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(point)
                            .icon(bitmap);
                    //在地图上添加Marker，并显示
                    mBaiduMap.addOverlay(option);
                }
                setCenter(poiInfos.get(0).location.latitude, poiInfos.get(0).location.longitude);
            } else {
                Toast.makeText(MapLocation.this, "定位失败", Toast.LENGTH_LONG).show();
                finish();
            }

        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        poiSearch.destroy();
        if (customDialog != null) {
            customDialog.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    /**
     * 确认
     *
     * @param view
     */
    public void check(View view) {
        Intent intent = new Intent(MapSelectViewNormal.THE_ACTION);
        intent.putExtra(IntentName.INTENT_LOCATION_NUMVIEW, numView);
        intent.putExtra("location", locationPoint);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);
        sendBroadcast(intent);
        finish();

    }

    @Override
    public void onMapClick(LatLng point) {
        mBaiduMap.hideInfoWindow();
    }

    CustomDialog customDialog;

    @Override
    public boolean onMapPoiClick(MapPoi latLng) {
        customDialog = new CustomDialog(MapLocation.this);
        customDialog.show();
        //获取经纬度
        double latitude = latLng.getPosition().latitude;
        double longitude = latLng.getPosition().longitude;
        this.longitude = longitude + "";
        this.latitude = latitude + "";
        LatLng point = new LatLng(latitude, longitude);
        mBaiduMap.clear();
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.zhy_map_point_2);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);

        // LatLng location; 点击事件得到的location
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    return;
                }
                if (customDialog != null) {
                    customDialog.cancel();
                }
                locationPoint = result.getAddress();
            }
        });
// 反向地理解析
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(point));

        return false;
    }
}

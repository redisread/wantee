package com.notekeeper.starter.grpc.wardrobe;

import build.buf.gen.google.rpc.Status;
import build.buf.gen.wardrobe.v1.CapsuleWardrobeServiceGrpc;
import build.buf.gen.wardrobe.v1.Clothing;
import build.buf.gen.wardrobe.v1.CreateClothingRequest;
import build.buf.gen.wardrobe.v1.CreateClothingResponse;
import build.buf.gen.wardrobe.v1.DeleteClothingRequest;
import build.buf.gen.wardrobe.v1.DeleteClothingResponse;
import build.buf.gen.wardrobe.v1.GetClothingRequest;
import build.buf.gen.wardrobe.v1.GetClothingResponse;
import build.buf.gen.wardrobe.v1.ListClothingRequest;
import build.buf.gen.wardrobe.v1.ListClothingResponse;
import build.buf.gen.wardrobe.v1.UpdateClothingRequest;
import build.buf.gen.wardrobe.v1.UpdateClothingResponse;
import com.notekeeper.infrastructure.dal.mapper.CountryMapper;
import com.notekeeper.infrastructure.dal.model.CountryDO;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.Resource;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author Redisread
 * @date 2024/12/15
 */
@GrpcService
public class CapsuleWardrobeService extends CapsuleWardrobeServiceGrpc.CapsuleWardrobeServiceImplBase {

    @Resource
    private CountryMapper countryMapper;

    @Override
    public void createClothing(CreateClothingRequest request, StreamObserver<CreateClothingResponse> responseObserver) {
        super.createClothing(request, responseObserver);
    }

    @Override
    public void updateClothing(UpdateClothingRequest request, StreamObserver<UpdateClothingResponse> responseObserver) {
        super.updateClothing(request, responseObserver);
    }

    @Override
    public void deleteClothing(DeleteClothingRequest request, StreamObserver<DeleteClothingResponse> responseObserver) {
        super.deleteClothing(request, responseObserver);
    }

    @Override
    public void getClothing(GetClothingRequest request, StreamObserver<GetClothingResponse> responseObserver) {
        CountryDO country = countryMapper.queryByName("中国");
        Clothing clothing = Clothing.newBuilder().setName(country.getCountryName()).setId(country.getId()).build();
        GetClothingResponse response = GetClothingResponse.newBuilder()
            .setStatus(Status.newBuilder().setCode(0).setMessage("success").build())
            .setClothing(clothing)
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listClothing(ListClothingRequest request, StreamObserver<ListClothingResponse> responseObserver) {
        super.listClothing(request, responseObserver);
    }
}

package com.notekeeper.starter.grpc.wardrobe;

import build.buf.gen.wardrobe.v1.CapsuleWardrobeServiceGrpc;
import build.buf.gen.wardrobe.v1.CreateClothingRequest;
import build.buf.gen.wardrobe.v1.CreateClothingResponse;
import build.buf.gen.wardrobe.v1.DeleteClothingRequest;
import build.buf.gen.wardrobe.v1.DeleteClothingResponse;
import build.buf.gen.wardrobe.v1.UpdateClothingRequest;
import build.buf.gen.wardrobe.v1.UpdateClothingResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author Redisread
 * @date 2024/12/15
 */
@GrpcService
public class CapsuleWardrobeService extends CapsuleWardrobeServiceGrpc.CapsuleWardrobeServiceImplBase {

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
}

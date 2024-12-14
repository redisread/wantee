package com.notekeeper.starter.grpc.wardrobe;

import build.buf.gen.google.type.DateTime;
import build.buf.gen.pet.v1.DeletePetRequest;
import build.buf.gen.pet.v1.DeletePetResponse;
import build.buf.gen.pet.v1.GetPetRequest;
import build.buf.gen.pet.v1.GetPetResponse;
import build.buf.gen.pet.v1.Pet;
import build.buf.gen.pet.v1.PetStoreServiceGrpc;
import build.buf.gen.pet.v1.PetType;
import build.buf.gen.pet.v1.PutPetRequest;
import build.buf.gen.pet.v1.PutPetResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author Redisread
 * @date 2024/12/14
 */
@GrpcService
public class PetStoreService extends PetStoreServiceGrpc.PetStoreServiceImplBase {

    /**
     * buf 调用方式：buf curl --protocol grpc --http2-prior-knowledge \
     * --data '{"pet_id": "1"}' \
     * http://localhost:9090/pet.v1.PetStoreService/GetPet
     *
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void getPet(GetPetRequest request, StreamObserver<GetPetResponse> responseObserver) {
        Pet pet = Pet.newBuilder()
            .setPetType(PetType.PET_TYPE_HAMSTER)
            .setName("test").setPetId("1").
            setCreatedAt(DateTime.newBuilder().build())
            .build();
        GetPetResponse getPetResponse = GetPetResponse.newBuilder()
            .setPet(pet)
            .build();
        responseObserver.onNext(getPetResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void putPet(PutPetRequest request, StreamObserver<PutPetResponse> responseObserver) {
        super.putPet(request, responseObserver);
    }

    @Override
    public void deletePet(DeletePetRequest request, StreamObserver<DeletePetResponse> responseObserver) {
        super.deletePet(request, responseObserver);
    }
}

package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.AddressDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Address;
import it.epicode.BW2_EpicEnergyServices.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public String saveAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setCap(addressDto.getCap());

        addressRepository.save(address);

        return "Address with id " + address.getAddressId() + " correctly saved!";
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(int id) {
        return addressRepository.findById(id);
    }

    public Address updateAddress(int id, AddressDto addressDto) {
        Optional<Address> addressOptional = getAddressById(id);
        if (addressOptional.isPresent()) {
            Address address = new Address();
            address.setStreet(addressDto.getStreet());
            address.setNumber(addressDto.getNumber());
            address.setCap(addressDto.getCap());

            addressRepository.save(address);

        } else {
            throw new AddressNotFoundException("Address with id=" + id + " not found!");
        }
    }

    public String deleteAddress(int id) {
        Optional<Address> addressOptional = getAddressById(id);
        if (addressOptional.isPresent()) {
            addressRepository.deleteById(id);
            return "Address with id=" + id + " correctly deleted!";
        } else {
            throw new AddressNotFoundException("Address with id=" + id + " not found!");
        }
    }
}

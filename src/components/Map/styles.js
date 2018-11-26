import styled from 'styled-components';

import { MapComponent } from './Map';
import seventhFloor from '../../images/7floor.svg';

export const Map = styled(MapComponent)`
  background-image: url(${seventhFloor});
  flex: 1;

  border: 1px solid #333;
`;
